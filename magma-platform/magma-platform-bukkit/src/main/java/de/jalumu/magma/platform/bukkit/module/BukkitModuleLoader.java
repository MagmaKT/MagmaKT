package de.jalumu.magma.platform.bukkit.module;

import de.jalumu.magma.platform.MagmaPlatform;
import de.jalumu.magma.platform.base.module.ModuleLoader;
import org.bukkit.Bukkit;

import java.io.File;

public class BukkitModuleLoader extends ModuleLoader {

    public BukkitModuleLoader(MagmaPlatform platform, File moduleDirectory) {
        super(platform, moduleDirectory);
    }

    @Override
    protected boolean isPlatformPluginAvailable(String plugin) {
        return Bukkit.getPluginManager().getPlugin(plugin) != null;
    }

}
