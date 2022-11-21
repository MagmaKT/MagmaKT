package de.jalumu.magma.module.tablist;

import de.jalumu.magma.module.ModuleMeta;
import de.jalumu.magma.module.tablist.events.ConnectionEvents;
import de.jalumu.magma.module.tablist.handler.TablistHandler;
import de.jalumu.magma.module.BaseModule;
import de.jalumu.magma.platform.MagmaPlatform;
import de.jalumu.magma.platform.MagmaPlatformType;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@ModuleMeta(
        name = "MagmaTablistModule",
        version = "1.0.0",
        author = "Jalumu",
        description = "A tablist module for Magma",
        dependsPlugin = {"LuckPerms"}
)
public class MagmaTablistModule extends BaseModule {

    private Permission perms = null;

    @Override
    public void onEnable() {
        //TablistHandler.init(this);

        Bukkit.getPluginManager().registerEvents(new ConnectionEvents(this), (Plugin) getPlatform().getMagmaPluginInstance());

        Bukkit.getScheduler().runTaskTimer(getBukkit(), bukkitTask -> {
            TablistHandler.updateDecoration();
        }, 5, 20);

    }

    public JavaPlugin getBukkit() {
        return (JavaPlugin) getPlatform();
    }

}
