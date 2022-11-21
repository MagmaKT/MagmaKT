package de.jalumu.magma.platform.base.module;

import de.jalumu.magma.module.BaseModule;
import de.jalumu.magma.module.MagmaModule;
import de.jalumu.magma.module.ModuleMeta;
import de.jalumu.magma.platform.MagmaPlatform;
import io.github.classgraph.ClassGraph;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public abstract class ModuleLoader {

    private MagmaPlatform platform;

    private final File moduleDirectory;

    private ModuleClassLoader classLoader;

    private Set<MagmaModule> modules;

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
    }

    public void loadModules() {

        new ClassGraph().overrideClassLoaders(classLoader).enableAllInfo().scan().getClassesWithAnnotation(ModuleMeta.class).forEach(classInfo -> {
            try {

                if (classInfo.extendsSuperclass(BaseModule.class)) {
                    ModuleMeta meta = (ModuleMeta) classInfo.getAnnotationInfo(ModuleMeta.class).loadClassAndInstantiate();
                    log.info("Found module " + meta.name() + " version:" + meta.version() + " by " + meta.author());

                    AtomicBoolean compatible = new AtomicBoolean(true);

                    Arrays.stream(meta.dependsPlugin()).forEach(plugin -> {
                        if (!isPlatformPluginAvailable(plugin)) {
                            compatible.set(false);
                            log.warn("Module " + meta.name() + " is missing plugin: " + plugin);
                        }
                    });

                    if (compatible.get()) {
                        BaseModule module = (BaseModule) classInfo.loadClass().newInstance();
                        module.setMeta(meta);
                        module.setPlatform(platform);
                        module.setDataFolder(new File(moduleDirectory, meta.name()));

                        module.getDataFolder().mkdirs();

                        module.onLoad();
                        modules.add(module);
                        log.info("Module " + meta.name() + " loaded");
                    } else {
                        log.warn("Module " + meta.name() + " is not compatible with the current platform. Skipping...");
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void enableCompatibleModules() {
        modules.stream().forEach(module -> {
            module.onEnable();
            log.info("Module " + module.getMeta().name() + " enabled");
        });
    }

    protected abstract boolean isPlatformPluginAvailable(String plugin);


}
