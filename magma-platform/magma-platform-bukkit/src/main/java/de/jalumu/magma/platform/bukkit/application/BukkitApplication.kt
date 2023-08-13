package de.jalumu.magma.platform.bukkit.application

import de.exlll.configlib.ConfigLib
import de.exlll.configlib.YamlConfigurationProperties
import de.jalumu.magma.command.MagmaCommand
import de.jalumu.magma.platform.base.application.MagmaApplicationBase
import de.jalumu.magma.platform.bukkit.command.MagmaBukkitCommandAnnotationReplacer
import net.kyori.adventure.platform.AudienceProvider
import net.kyori.adventure.platform.bukkit.BukkitAudiences
import org.bukkit.plugin.java.JavaPlugin
import revxrsal.commands.CommandHandler
import revxrsal.commands.bukkit.BukkitCommandHandler

abstract class BukkitApplication : JavaPlugin(), MagmaApplicationBase {

    override lateinit var commandHandler: BukkitCommandHandler

    private var adventure: BukkitAudiences? = null
    var configurationProperties: YamlConfigurationProperties.Builder<*>? = null
        private set

    private fun adventure(): BukkitAudiences? {
        checkNotNull(adventure) { "Tried to access Adventure when the plugin was disabled!" }
        return adventure
    }

    override fun onLoad() {
        val configFolder = dataFolder
        if (!configFolder.exists()) {
            configFolder.mkdirs()
        }
        initialize()
    }

    override fun onEnable() {
        adventure = BukkitAudiences.create(this)
        configurationProperties = ConfigLib.BUKKIT_DEFAULT_PROPERTIES.toBuilder()
        commandHandler = BukkitCommandHandler.create(this)
        commandHandler.registerAnnotationReplacer(MagmaCommand::class.java, MagmaBukkitCommandAnnotationReplacer())
        start()
    }

    override fun onDisable() {
        shutdown()
        if (adventure != null) {
            adventure!!.close()
            adventure = null
        }
    }

    override val audience: AudienceProvider?
        get() = adventure()
}
