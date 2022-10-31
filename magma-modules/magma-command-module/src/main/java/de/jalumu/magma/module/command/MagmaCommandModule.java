package de.jalumu.magma.module.command;

import de.jalumu.magma.module.command.events.BukkitCommandEvents;
import de.jalumu.magma.platform.base.module.ModuleBase;
import de.jalumu.magma.platform.base.platform.MagmaPlatform;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class MagmaCommandModule extends ModuleBase {

    public MagmaCommandModule(MagmaPlatform platform, File dataFolder) {
        super(platform, dataFolder);
    }

    @Override
    public String getName() {
        return "Magma-Command";
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new BukkitCommandEvents(this), getBukkit());
    }

    public JavaPlugin getBukkit() {
        return (JavaPlugin) getPlatform();
    }

    @Override
    public boolean isCompatible() {
        return true;
    }
}
