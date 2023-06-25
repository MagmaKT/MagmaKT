package de.jalumu.magma.platform.bukkit.module

import de.jalumu.magma.platform.MagmaPlatform
import de.jalumu.magma.platform.base.module.ModuleLoader
import org.bukkit.Bukkit
import java.io.File

class BukkitModuleLoader(platform: MagmaPlatform?, moduleDirectory: File?) : ModuleLoader(platform, moduleDirectory) {
    override fun isPlatformPluginAvailable(plugin: String): Boolean {
        return Bukkit.getPluginManager().getPlugin(plugin) != null
    }
}