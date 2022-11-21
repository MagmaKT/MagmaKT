package de.jalumu.magma.platform.base.module;

import de.jalumu.magma.module.BaseModule;
import de.jalumu.magma.module.MagmaModule;
import de.jalumu.magma.module.ModuleMeta;
import de.jalumu.magma.platform.MagmaPlatform;
import io.github.classgraph.ClassGraph;
import lombok.Getter;
import lombok.extern.java.Log;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@Log(topic = "MagmaKT-ModuleLoader")
public abstract class ModuleLoader {

    @Getter
    private MagmaPlatform platform;

    private final File moduleDirectory;

    private ModuleClassLoader classLoader;

    private Set<MagmaModule> modules;

    private Set<MagmaModule> enabledModules;

    public ModuleLoader(MagmaPlatform platform, File moduleDirectory) {
        this.platform = platform;
        this.moduleDirectory = moduleDirectory;
        classLoader = new ModuleClassLoader(this.getClass().getClassLoader());
    }


    public void prepare() {
        if (!moduleDirectory.exists()) {
            moduleDirectory.mkdirs();
        }
        Arrays.stream(moduleDirectory.listFiles()).forEach(file -> {
            try {
                if (file.getName().endsWith(".jar")) {
                    classLoader.addJar(file);
                    log.info("Added " + file.getName() + " to the classpath");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        modules = new HashSet<>();
        enabledModules = new HashSet<>();
    }

    public void loadModules() {

        new ClassGraph().overrideClassLoaders(classLoader).enableAllInfo().scan().getClassesWithAnnotation(ModuleMeta.class).forEach(classInfo -> {
            try {

                if (classInfo.extendsSuperclass(BaseModule.class)) {
                    ModuleMeta meta = (ModuleMeta) classInfo.getAnnotationInfo(ModuleMeta.class).loadClassAndInstantiate();
                    log.info("Found module " + meta.name() + " version:" + meta.version() + " by " + meta.author());

                    AtomicBoolean compatible = new AtomicBoolean(true);

                    if (Arrays.stream(meta.supportedPlatforms()).noneMatch(supportedPlatform -> platform.getPlatformType().equals(supportedPlatform))) {
                        compatible.set(false);
                    }

                    if (Arrays.stream(meta.supportedServerImplementations()).noneMatch(serverImplementation -> platform.getServerImplementation().equals(serverImplementation))) {
                        compatible.set(false);
                    }

                    Arrays.stream(meta.dependsPlugin()).forEach(plugin -> {
                        if (!isPlatformPluginAvailable(plugin)) {
                            compatible.set(false);
                            log.warning("Module " + meta.name() + " is missing plugin: " + plugin);
                        }
                    });

                    if (compatible.get()) {
                        BaseModule module = (BaseModule) classInfo.loadClass().newInstance();
                        module.setMeta(meta);
                        module.setPlatform(platform);
                        module.setDataFolder(new File(moduleDirectory, meta.name()));
                        module.setLogger(new ModuleLogger(meta.name(), platform.getLogger()));

                        module.getDataFolder().mkdirs();

                        module.onLoad();
                        modules.add(module);
                        log.info("Module " + meta.name() + " loaded");
                    } else {
                        log.warning("Module " + meta.name() + " is not compatible. Skipping...");
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void enableCompatibleModules() {
        modules.forEach(module -> {

            AtomicBoolean compatible = new AtomicBoolean(true);

            Arrays.stream(module.getMeta().dependsModule()).forEach(requiredModule -> {
                if (modules.stream().noneMatch(magmaModule -> magmaModule.getMeta().name().equals(requiredModule))) {
                    compatible.set(false);
                    log.warning("Module " + module.getMeta().name() + " is missing module: " + requiredModule);
                }
            });

            if (compatible.get()) {
                module.onEnable();
                enabledModules.add(module);
                log.info("Module " + module.getMeta().name() + " enabled");
            } else {
                log.warning("Module " + module.getMeta().name() + " is not compatible. Skipping...");
            }

        });
    }

    public void disableModules() {
        enabledModules.forEach(module -> {
            module.onDisable();
            log.info("Module " + module.getMeta().name() + " disabled");
        });
        enabledModules.clear();
    }

    protected abstract boolean isPlatformPluginAvailable(String plugin);


}
