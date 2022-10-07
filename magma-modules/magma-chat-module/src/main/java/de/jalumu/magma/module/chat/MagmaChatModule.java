package de.jalumu.magma.module.chat;

import de.jalumu.magma.module.chat.events.ChatEvents;
import de.jalumu.magma.platform.base.module.MagmaModule;
import de.jalumu.magma.platform.base.module.ModuleBase;
import de.jalumu.magma.platform.base.platform.MagmaPlatform;
import de.jalumu.magma.platform.base.platform.MagmaPlatformType;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class MagmaChatModule extends ModuleBase {


    public MagmaChatModule(MagmaPlatform platform, File dataFolder) {
        super(platform, dataFolder);
    }

    @Override
    public String getName() {
        return "Magma-Chat";
    }

    @Override
    public void onEnable() {
        //platform.getLogger().info("Enable");
        Bukkit.getPluginManager().registerEvents(new ChatEvents(this), (Plugin) getPlatform().getMagmaPluginInstance());
    }

    @Override
    public boolean isCompatible() {
        return getPlatform().getPlatformType() == MagmaPlatformType.GAMESERVER;
    }

    public JavaPlugin getBukkit() {
        return (JavaPlugin) getPlatform();
    }

}
