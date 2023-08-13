package de.jalumu.magma.platform.base.application

import net.kyori.adventure.platform.AudienceProvider
import revxrsal.commands.CommandHandler

interface MagmaApplicationBase {
    fun initialize()
    fun start()
    fun shutdown()
    val commandHandler: CommandHandler?
    val audience: AudienceProvider?
}
