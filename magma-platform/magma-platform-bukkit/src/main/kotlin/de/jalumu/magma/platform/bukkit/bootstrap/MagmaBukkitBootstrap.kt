package de.jalumu.magma.platform.bukkit.bootstrap

import de.exlll.configlib.YamlConfigurationProperties
import de.exlll.configlib.YamlConfigurations
import de.jalumu.magma.annotation.bukkit.platform.application.BukkitPlugin
import de.jalumu.magma.command.MagmaCommand
import de.jalumu.magma.platform.MagmaPlatform
import de.jalumu.magma.platform.MagmaPlatformProvider
import de.jalumu.magma.platform.MagmaPlatformType
import de.jalumu.magma.platform.ServerImplementation
import de.jalumu.magma.platform.base.config.ServerIdConfig
import de.jalumu.magma.platform.base.config.serializer.TextSerializer
import de.jalumu.magma.platform.base.module.ModuleLoader
import de.jalumu.magma.platform.base.platform.util.SplashScreen
import de.jalumu.magma.platform.bukkit.application.BukkitApplication
import de.jalumu.magma.platform.bukkit.command.MagmaBukkitCommandAnnotationReplacer
import de.jalumu.magma.platform.bukkit.database.BukkitMySQLManager
import de.jalumu.magma.platform.bukkit.module.BukkitModuleLoader
import de.jalumu.magma.platform.bukkit.player.BukkitPlayerProvider
import de.jalumu.magma.platform.bukkit.text.BukkitTextProvider
import de.jalumu.magma.platform.bukkit.text.notification.BukkitNotificationProvider
import de.jalumu.magma.platform.bukkit.text.placeholder.BukkitPlaceholderProvider
import de.jalumu.magma.player.PlayerProvider
import de.jalumu.magma.text.Text
import de.jalumu.magma.text.TextProvider
import de.jalumu.magma.text.notification.NotificationProvider
import de.jalumu.magma.text.placeholder.PlaceholderProvider
import org.bstats.bukkit.Metrics
import revxrsal.commands.CommandHandler
import revxrsal.commands.bukkit.BukkitCommandHandler
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

@BukkitPlugin(
    name = "MagmaKT-Bukkit",
    version = "Dev-Build",
    description = "MagmaKT for Bukkit",
    author = "JaLuMu",
    dependsPlugin = [],
    softDependsPlugin = ["ProtocolLib", "LuckPerms"]
)
class MagmaBukkitBootstrap : BukkitApplication(), MagmaPlatform {

    private lateinit var moduleLoader: ModuleLoader

    private var mySQLManager: BukkitMySQLManager? = null

    private lateinit var commandHandler: BukkitCommandHandler

    private var serverIdConfig: ServerIdConfig? = null

    private var serverID: String? = null
    override fun initialize() {
        MagmaPlatformProvider.setPlatform(this)
        try {
            Files.createDirectories(Paths.get(dataFolder.toPath().toString() + File.separator + "modules"))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun start() {
        val metrics = Metrics(this, 16417)

        val serverIdFile = File(dataFolder, "serverID.yml")

        val configurationProperties = YamlConfigurationProperties.newBuilder().addSerializer(
            Text::class.java, TextSerializer()
        ).createParentDirectories(true).build()

        if (serverIdFile.exists()) {
            serverIdConfig = YamlConfigurations.load(serverIdFile.toPath(), ServerIdConfig::class.java)
        } else {
            serverIdConfig = ServerIdConfig("Unknown")
            YamlConfigurations.save(serverIdFile.toPath(), ServerIdConfig::class.java, serverIdConfig)
        }

        serverID = serverIdConfig!!.serverID

        TextProvider.setTextProvider(BukkitTextProvider())
        NotificationProvider.setProvider(BukkitNotificationProvider(this))
        PlaceholderProvider.setProvider(BukkitPlaceholderProvider(this))
        PlayerProvider.setProvider(BukkitPlayerProvider(this))

        commandHandler = BukkitCommandHandler.create(this)
        commandHandler.registerAnnotationReplacer(MagmaCommand::class.java, MagmaBukkitCommandAnnotationReplacer())

        moduleLoader = BukkitModuleLoader(this, File(dataFolder.toPath().toString() + File.separator + "modules"))
        moduleLoader.prepare()
        moduleLoader.loadModules()
        moduleLoader.enableCompatibleModules()

        SplashScreen.splashScreen(this)

        getCommand("magma")!!.setExecutor(de.jalumu.magma.platform.bukkit.command.MagmaCommand(this))

        mySQLManager = BukkitMySQLManager(this)
    }

    override fun shutdown() {
        moduleLoader.disableModules()
        mySQLManager!!.database?.shutdown()
    }

    override fun getVersion(): String {
        return description.version
    }

    override fun getPlatformType(): MagmaPlatformType {
        return MagmaPlatformType.GAMESERVER
    }

    override fun getServerImplementation(): ServerImplementation {
        return ServerImplementation.PAPER
    }

    override fun getPlatformName(): String {
        return server.name
    }

    override fun getPlatformVersion(): String {
        return server.version
    }

    override fun getServerID(): String {
        return serverID!!
    }

    override fun setServerID(serverID: String) {
        this.serverID = serverID
    }

    override fun getCommandHandler(): CommandHandler {
        return commandHandler
    }

    override fun getMagmaPluginInstance(): Any {
        return this
    }
}