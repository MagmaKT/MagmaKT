package de.jalumu.magma.platform.bungee.bootstrap

import de.exlll.configlib.YamlConfigurations
import de.jalumu.magma.annotation.bungee.platform.application.BungeecordPlugin
import de.jalumu.magma.platform.MagmaPlatform
import de.jalumu.magma.platform.MagmaPlatformProvider
import de.jalumu.magma.platform.MagmaPlatformType
import de.jalumu.magma.platform.ServerImplementation
import de.jalumu.magma.platform.base.config.ServerIdConfig
import de.jalumu.magma.platform.base.module.ModuleLoader
import de.jalumu.magma.platform.base.platform.util.SplashScreen
import de.jalumu.magma.platform.bungee.application.BungeeApplication
import de.jalumu.magma.platform.bungee.module.BungeeModuleLoader
import de.jalumu.magma.platform.bungee.player.BungeePlayerProvider
import de.jalumu.magma.platform.bungee.text.BungeeTextProvider
import de.jalumu.magma.platform.bungee.text.notification.BungeeNotificationProvider
import de.jalumu.magma.platform.bungee.text.placeholder.BungeePlaceholderProvider
import de.jalumu.magma.player.PlayerProvider
import de.jalumu.magma.text.TextProvider
import de.jalumu.magma.text.notification.NotificationProvider
import de.jalumu.magma.text.placeholder.PlaceholderProvider
import java.io.File
import java.util.logging.Logger

@BungeecordPlugin(
    name = "MagmaKT-Bungee",
    version = "0.0.1",
    description = "MagmaKT for Bungeecord",
    author = "JaLuMu",
    dependsPlugin = []
)
class MagmaBungeeBootstrap : BungeeApplication(), MagmaPlatform {


    override val magmaImplementationName: String
        get() {
            return this.description.name
        }
    override val magmaImplementationVersion: String
        get() {
            return this.description.version
        }
    override val platformType: MagmaPlatformType = MagmaPlatformType.PROXY
    override val serverImplementation: ServerImplementation = ServerImplementation.BUNGEECORD
    override val platformName: String
        get() {
            return proxy.name
        }
    override val platformVersion: String
        get() {
            return proxy.version
        }
    override val magmaLogger: Logger
        get() = this.logger
    override val magmaPluginInstance: Any
        get() = this

    private lateinit var moduleLoader: ModuleLoader
    private lateinit var serverIdConfig: ServerIdConfig
    override var serverID: String? = null
    override fun initialize() {
        MagmaPlatformProvider.setPlatform(this)
    }

    override fun start() {
        val serverIdFile = File(dataFolder, "serverID.yml")
        if (serverIdFile.exists()) {
            serverIdConfig = YamlConfigurations.load(serverIdFile.toPath(), ServerIdConfig::class.java)
        } else {
            serverIdConfig = ServerIdConfig("Proxy")
            YamlConfigurations.save(serverIdFile.toPath(), ServerIdConfig::class.java, serverIdConfig)
        }
        serverID = serverIdConfig!!.serverID
        PlayerProvider.setProvider(BungeePlayerProvider(this))
        TextProvider.setTextProvider(BungeeTextProvider())
        val notificationProvider = BungeeNotificationProvider(this)
        NotificationProvider.setProvider(notificationProvider)
        notificationProvider.init()
        PlaceholderProvider.setProvider(BungeePlaceholderProvider())
        moduleLoader = BungeeModuleLoader(this, File(this.dataFolder.toPath().toString() + File.separator + "modules"))
        moduleLoader.prepare()
        moduleLoader.loadModules()
        moduleLoader.enableCompatibleModules()
        SplashScreen.splashScreen(this)
    }

    override fun shutdown() {
        moduleLoader!!.disableModules()
    }

}
