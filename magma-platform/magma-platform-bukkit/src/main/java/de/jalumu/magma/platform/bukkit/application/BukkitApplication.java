package de.jalumu.magma.platform.bukkit.application;

import de.jalumu.magma.platform.base.application.MagmaApplicationBase;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public abstract class BukkitApplication extends JavaPlugin implements MagmaApplicationBase {

    @Override
    public void onLoad() {

        File configFolder = getDataFolder();

        if (!configFolder.exists()) {
            configFolder.mkdirs();
        }

        initialize();
    }

    @Override
    public void onEnable() {
        start();
    }

    @Override
    public void onDisable() {
        shutdown();
    }
}
