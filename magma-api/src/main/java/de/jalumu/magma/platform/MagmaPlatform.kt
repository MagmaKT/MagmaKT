package de.jalumu.magma.platform

import revxrsal.commands.CommandHandler
import java.util.logging.Logger

interface MagmaPlatform {
    val magmaImplementationName: String
    val magmaImplementationVersion: String
    val platformType: MagmaPlatformType
    val serverImplementation: ServerImplementation
    val platformName: String
    val platformVersion: String
    var serverID: String?
    val magmaLogger: Logger
    val commandHandler: CommandHandler
    val magmaPluginInstance: Any

    companion object {
        val platform: MagmaPlatform?
            get() = MagmaPlatformProvider.getPlatform()
    }
}
