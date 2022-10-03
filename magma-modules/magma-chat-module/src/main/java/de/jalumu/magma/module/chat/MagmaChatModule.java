package de.jalumu.magma.module.chat;

import de.jalumu.magma.module.chat.events.ChatEvents;
import de.jalumu.magma.platform.base.module.MagmaModule;
import de.jalumu.magma.platform.base.platform.MagmaPlatform;
import de.jalumu.magma.platform.base.platform.MagmaPlatformType;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class MagmaChatModule implements MagmaModule {

    private MagmaPlatform platform;

    @Override
    public String getName() {
        return "Magma-Chat";
    }

    @Override
    public void onLoad() {
        platform.getLogger().info("Load");
    }

    @Override
    public void onEnable() {
        platform.getLogger().info("Enable");
        Bukkit.getPluginManager().registerEvents(new ChatEvents(this), (Plugin) platform.getMagmaPluginInstance());
    }

    @Override
    public void onDisable() {
        platform.getLogger().info("Disable");
    }

    @Override
    public void onUnload() {
        platform.getLogger().info("Unload");
    }

    @Override
    public MagmaPlatform getPlatform() {
        return platform;
    }

    @Override
    public boolean isCompatible(MagmaPlatform platform) {
        if (platform.getPlatformType() == MagmaPlatformType.GAMESERVER){
            this.platform = platform;
            return true;
        }
        return false;
    }
}
