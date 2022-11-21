package de.jalumu.magma.module.chat;

import de.jalumu.magma.module.ModuleMeta;
import de.jalumu.magma.module.chat.events.ChatEvents;
import de.jalumu.magma.module.chat.events.ConnectionEvents;
import de.jalumu.magma.module.BaseModule;
import de.jalumu.magma.platform.MagmaPlatform;
import de.jalumu.magma.platform.MagmaPlatformType;
import de.jalumu.magma.platform.ServerImplementation;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@ModuleMeta(
        name = "MagmaChatModule",
        version = "1.0",
        author = "Jalumu",
        description = "A chat module for Magma",
        supportedPlatforms = {MagmaPlatformType.STANDALONE},
        supportedServerImplementations = {ServerImplementation.PAPER},
        dependsPlugin = {"PlaceholderAPI"})
public class MagmaChatModule extends BaseModule {

    @Override
    public void onEnable() {
        getPlatform().getLogger().info("MagmaChatModule is enabled!");
        Bukkit.getPluginManager().registerEvents(new ChatEvents(this), (Plugin) getPlatform().getMagmaPluginInstance());
        Bukkit.getPluginManager().registerEvents(new ConnectionEvents(this), (Plugin) getPlatform().getMagmaPluginInstance());
    }

    public JavaPlugin getBukkit() {
        return (JavaPlugin) getPlatform();
    }

}
