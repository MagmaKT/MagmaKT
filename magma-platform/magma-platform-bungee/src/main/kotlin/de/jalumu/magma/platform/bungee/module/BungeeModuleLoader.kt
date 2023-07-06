package de.jalumu.magma.platform.bungee.module

import de.jalumu.magma.platform.MagmaPlatform
import de.jalumu.magma.platform.MagmaPlatform.Companion.platform
import de.jalumu.magma.platform.base.module.ModuleLoader
import de.jalumu.magma.platform.bungee.bootstrap.MagmaBungeeBootstrap
import java.io.File

class BungeeModuleLoader(platform: MagmaPlatform, moduleDirectory: File) : ModuleLoader(platform, moduleDirectory) {
    override fun isPlatformPluginAvailable(plugin: String?): Boolean {
        return (platform?.magmaPluginInstance as MagmaBungeeBootstrap).proxy.pluginManager.getPlugin(plugin) != null
    }
}
