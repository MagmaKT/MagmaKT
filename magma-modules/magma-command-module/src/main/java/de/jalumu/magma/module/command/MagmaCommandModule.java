package de.jalumu.magma.module.command;

import de.jalumu.magma.module.BaseModule;
import de.jalumu.magma.module.ModuleMeta;
import de.jalumu.magma.module.command.events.BukkitCommandEvents;
import de.jalumu.magma.platform.MagmaPlatformType;
import de.jalumu.magma.platform.ServerImplementation;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@ModuleMeta(
        name = "MagmaCommandModule",
        version = "1.0.0",
        author = "Jalumu",
        description = "A command module for Magma",
        supportedPlatforms = {MagmaPlatformType.GAMESERVER},
        supportedServerImplementations = {ServerImplementation.PAPER}
)
public class MagmaCommandModule extends BaseModule {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new BukkitCommandEvents(this), (JavaPlugin) getPlatform().getMagmaPluginInstance());
    }

}
