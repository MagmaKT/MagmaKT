package de.jalumu.magma.platform.paper.bootstrap;

import de.jalumu.magma.annotation.bukkit.platform.application.BukkitPlugin;
import de.jalumu.magma.platform.base.platform.MagmaPlatform;
import de.jalumu.magma.platform.base.platform.MagmaPlatformType;
import de.jalumu.magma.platform.base.platform.util.SplashScreen;
import org.bukkit.plugin.java.JavaPlugin;

@BukkitPlugin(name = "MagmaKT-Bukkit", version = "0.0.1", description = "MagmaKT for Bukkit", author = "JaLuMu", dependsPlugin = {})
public class MagmaPaperBootstrap extends JavaPlugin implements MagmaPlatform {

    @Override
    public void onEnable() {
        SplashScreen.splashScreen(this);
    }

    @Override
    public String getVersion() {
        return "Unknown";
    }

    @Override
    public MagmaPlatformType getPlatformType() {
        return MagmaPlatformType.GAMESERVER;
    }

    @Override
    public String getPlatformName() {
        return getServer().getName();
    }

    @Override
    public String getPlatformVersion() {
        return getServer().getVersion();
    }


}
