package de.keeeks.magma.core.api;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class ModuleLoader {
    private static final File FOLDER = new File("magma-kt", "modules");

    @Getter
    @Accessors(fluent = true)
    private final List<Module> enabled = new LinkedList<>();
    @Getter
    @Accessors(fluent = true)
    private final List<Module> disabled = new LinkedList<>();
    @Getter
    @Accessors(fluent = true)
    private final List<Module> initialized = new LinkedList<>();

    @Getter
    @Accessors(fluent = true)
    private final Logger logger;
    private final ModuleClassLoader classLoader;
    private final TopologicalSorting sorting;

    public static ModuleLoader create(Logger logger,
                                      ClassLoader classLoader) {
        return new ModuleLoader(
                logger,
                ModuleClassLoader.create(classLoader),
                new TopologicalSorting(logger)
        );
    }

    @SneakyThrows
    public void load() {
        if (!FOLDER.exists()) {
            FOLDER.mkdirs();
        }

        var moduleFiles = moduleFiles();
        for (var file : moduleFiles) {
            loadUrl(file);
        }

        try (var scan = new ClassGraph()
                .acceptPaths()
                .overrideClassLoaders(classLoader)
                .enableAllInfo()
                .scan()) {
            for (var classInfo : scan.getClassesWithAnnotation(ModuleDescription.class)) {
                loadClassAndAddToSorting(classInfo);
            }
        }
    }

    public void initialize() {
        var sorted = sorting.sorted();
        sorted.stream().filter(container ->
                container.enabled().get()
        ).forEach(container -> {
            var module = createModule(container.mainClass());
            module.updateLogger(ModuleLogger.create(
                    module,
                    logger
            ));
            initialized.add(module);
        });
        initialized.forEach(module -> {
            try {
                module.initialize();
                logger.info("Loaded module %s".formatted(
                        module.description().name()
                ));
            } catch (Throwable throwable) {
                logger.log(
                        Level.SEVERE,
                        "Could not load module %s".formatted(module.description().name()),
                        throwable
                );
            }
        });
    }

    public void enable() {
        initialized.forEach(module -> {
            logger.info("Enabling module %s...".formatted(
                    module.description().name()
            ));
            module.enable();
            enabled.add(module);
        });
    }

    public void disable() {
        enabled.forEach(module -> disable(module, false));
        enabled.clear();
    }

    public void disable(Module module, boolean remove) {
        logger.info("Disabling module %s...".formatted(
                module.description().name()
        ));
        module.disable();
        disabled.add(module);
        if (!remove) {
            return;
        }
        enabled.removeIf(enabledModule -> enabledModule.description().name().equals(module.description().name()));
    }

    @SneakyThrows
    private void loadUrl(File file) {
        classLoader.addURL(file.toURI().toURL());
    }

    private File[] moduleFiles() {
        return Objects.requireNonNull(FOLDER.listFiles((dir, name) -> name.endsWith(".jar")));
    }

    private void loadClassAndAddToSorting(ClassInfo classInfo) {
        Class<?> loadedClass = classInfo.loadClass();
        var description = loadedClass.getDeclaredAnnotation(
                ModuleDescription.class
        );
        sorting.add(
                description,
                (Class<? extends Module>) loadedClass
        );
    }

    @SneakyThrows
    private Module createModule(Class<? extends Module> mainClazz) {
        return mainClazz.getConstructor().newInstance();
    }
}