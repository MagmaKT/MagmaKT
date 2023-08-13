package de.jalumu.magma.platform.bungee.application

import de.exlll.configlib.YamlConfigurationProperties
import de.jalumu.magma.command.MagmaCommand
import de.jalumu.magma.platform.base.application.MagmaApplicationBase
import de.jalumu.magma.platform.bungee.command.MagmaBungeeCommandAnnotationReplacer
import net.kyori.adventure.platform.AudienceProvider
import net.kyori.adventure.platform.bungeecord.BungeeAudiences
import net.md_5.bungee.api.plugin.Plugin
import revxrsal.commands.CommandHandler
import revxrsal.commands.bungee.BungeeCommandHandler

abstract class BungeeApplication : Plugin(), MagmaApplicationBase {
    override lateinit var commandHandler: CommandHandler
    private var adventure: BungeeAudiences? = null
    override val audience: AudienceProvider?
        get() = adventure()

    private var configurationProperties: YamlConfigurationProperties.Builder<*>? = null
    private fun adventure(): BungeeAudiences? {
        checkNotNull(adventure) { "Cannot retrieve audience provider while plugin is not enabled" }
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
        adventure = BungeeAudiences.create(this)
        configurationProperties = YamlConfigurationProperties.newBuilder()
        commandHandler = BungeeCommandHandler.create(this)
        commandHandler.registerAnnotationReplacer(MagmaCommand::class.java, MagmaBungeeCommandAnnotationReplacer())
        start()
    }

    override fun onDisable() {
        shutdown()
        if (adventure != null) {
            adventure!!.close()
            adventure = null
        }
    }
}
