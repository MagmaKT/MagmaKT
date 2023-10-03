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
import de.jalumu.magma.platform.base.ext.discord.DiscordWebhook
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
import de.jalumu.magma.text.notification.Notification
import de.jalumu.magma.text.notification.NotificationProvider
import de.jalumu.magma.text.placeholder.PlaceholderProvider
import de.jalumu.magma.util.sandbox.Sandbox
import org.bstats.bukkit.Metrics
import revxrsal.commands.bukkit.BukkitCommandHandler
import java.io.File
import java.io.IOException
import java.lang.Exception
import java.nio.file.Files
import java.nio.file.Paths
import java.util.logging.Logger

/**
 * The entrypoint for the MagmaKT BukkitPlugin
 */
@BukkitPlugin(
    name = "MagmaKT-Bukkit",
    version = "Dev-Build",
    description = "MagmaKT for Bukkit",
    author = "JaLuMu",
    dependsPlugin = [],
    softDependsPlugin = ["ProtocolLib", "LuckPerms", "PlaceholderAPI","SimpleCloud-Plugin"]
)
class MagmaBukkitBootstrap : BukkitApplication(), MagmaPlatform {

    private lateinit var moduleLoader: ModuleLoader

    private var mySQLManager: BukkitMySQLManager? = null

    override lateinit var commandHandler: BukkitCommandHandler

    private var serverIdConfig: ServerIdConfig? = null

    override val magmaImplementationName: String
        get() {
            return pluginMeta.name
        }
    override val magmaImplementationVersion: String
        get() = pluginMeta.version

    override val platformType: MagmaPlatformType = MagmaPlatformType.GAMESERVER
    override val serverImplementation: ServerImplementation = ServerImplementation.PAPER

    override var serverID: String? = null
    override val platformName: String
        get() {
            return server.name

        }
    override val platformVersion: String
        get() {
            return server.version
        }
    override val magmaLogger: Logger
        get() {
            return super.getLogger()
        }
    override val magmaPluginInstance: Any = this

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

        Sandbox.register("discord", Sandbox { player, platform ->
            val webhook = DiscordWebhook("https://discord.com/api/webhooks/1130235531696033924/gQqFW4HuHyc7Dw63oavrKTSE-uKBTokJkKgV585Rk5QIleBSFh7RVylLm6SMY0fQxyla").apply {
                setUsername("MagmaKT-Error-Handler")
            }
            webhook.execute()
            Notification.info("Message send").send(player.audience)
        })

    }

    override fun shutdown() {
        moduleLoader.disableModules()
        mySQLManager!!.database?.shutdown()
    }


}