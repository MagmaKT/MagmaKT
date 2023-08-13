package de.jalumu.magma.platform.base.module

import de.jalumu.magma.module.BaseModule
import de.jalumu.magma.module.MagmaModule
import de.jalumu.magma.module.ModuleMeta
import de.jalumu.magma.platform.MagmaPlatform
import de.jalumu.magma.platform.MagmaPlatformType
import de.jalumu.magma.platform.ServerImplementation
import io.github.classgraph.ClassGraph
import io.github.classgraph.ClassInfo
import lombok.Getter
import lombok.extern.java.Log
import java.io.File
import java.util.*
import java.util.concurrent.atomic.AtomicBoolean
import java.util.function.Consumer
import java.util.logging.Logger

@Log(topic = "MagmaKT-ModuleLoader")
abstract class ModuleLoader(@field:Getter private val platform: MagmaPlatform, private val moduleDirectory: File) {
    private val classLoader: ModuleClassLoader = ModuleClassLoader(this.javaClass.classLoader)
    private var modules: MutableSet<MagmaModule>? = null
    private var enabledModules: MutableSet<MagmaModule>? = null

    private val log = Logger.getLogger(this.javaClass.name)

    fun prepare() {
        if (!moduleDirectory.exists()) {
            moduleDirectory.mkdirs()
        }
        Arrays.stream<File>(moduleDirectory.listFiles()).forEach { file: File ->
            try {
                if (file.name.endsWith(".jar")) {
                    classLoader.addJar(file)
                    log.info("Added " + file.name + " to the classpath")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        modules = HashSet()
        enabledModules = HashSet()
    }

    fun loadModules() {
        ClassGraph().overrideClassLoaders(classLoader).enableAllInfo().scan()
            .getClassesWithAnnotation(ModuleMeta::class.java).forEach(
            Consumer<ClassInfo> { classInfo: ClassInfo ->
                try {
                    if (classInfo.extendsSuperclass(BaseModule::class.java)) {
                        val meta =
                            classInfo.getAnnotationInfo(ModuleMeta::class.java).loadClassAndInstantiate() as ModuleMeta
                        log.info("Found module " + meta.name + " version:" + meta.version + " by " + meta.author)
                        val compatible = AtomicBoolean(true)
                        if (Arrays.stream(meta.supportedPlatforms)
                                .noneMatch { supportedPlatform: MagmaPlatformType -> platform.platformType == supportedPlatform }
                        ) {
                            compatible.set(false)
                        }
                        if (Arrays.stream(meta.supportedServerImplementations)
                                .noneMatch { serverImplementation: ServerImplementation -> platform.serverImplementation == serverImplementation }
                        ) {
                            compatible.set(false)
                        }
                        Arrays.stream<String>(meta.dependsPlugin).forEach { plugin: String ->
                            if (!isPlatformPluginAvailable(plugin)) {
                                compatible.set(false)
                                log.warning("Module " + meta.name + " is missing plugin: " + plugin)
                            }
                        }
                        if (compatible.get()) {
                            val module = classInfo.loadClass().newInstance() as BaseModule
                            module.meta = meta
                            module.platform = platform
                            module.dataFolder = File(moduleDirectory, meta.name)
                            module.logger = ModuleLogger(meta.name, platform.magmaLogger)
                            module.dataFolder.mkdirs()
                            module.onLoad()
                            modules!!.add(module)
                            log.info("Module " + meta.name + " loaded")
                        } else {
                            log.warning("Module " + meta.name + " is not compatible. Skipping...")
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            })
    }

    fun enableCompatibleModules() {
        modules!!.forEach(Consumer<MagmaModule> { module: MagmaModule ->
            val compatible = AtomicBoolean(true)
            Arrays.stream<String>(module.meta.dependsModule).forEach { requiredModule: String ->
                if (modules!!.stream()
                        .noneMatch { magmaModule: MagmaModule -> magmaModule.meta.name == requiredModule }
                ) {
                    compatible.set(false)
                    log.warning("Module " + module.meta.name + " is missing module: " + requiredModule)
                }
            }
            if (compatible.get()) {
                module.onEnable()
                enabledModules!!.add(module)
                log.info("Module " + module.meta.name + " enabled")
            } else {
                log.warning("Module " + module.meta.name + " is not compatible. Skipping...")
            }
        })
    }

    fun disableModules() {
        enabledModules!!.forEach(Consumer<MagmaModule> { module: MagmaModule ->
            module.onDisable()
            log.info("Module " + module.meta.name + " disabled")
        })
        enabledModules!!.clear()
    }

    protected abstract fun isPlatformPluginAvailable(plugin: String?): Boolean
}
