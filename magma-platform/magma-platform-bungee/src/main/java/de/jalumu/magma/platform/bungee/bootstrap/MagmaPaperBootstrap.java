package de.jalumu.magma.platform.bungee.bootstrap;

import de.jalumu.magma.annotation.bungee.platform.application.BungeecordPlugin;
import de.jalumu.magma.platform.base.platform.MagmaPlatform;
import de.jalumu.magma.platform.base.platform.MagmaPlatformType;
import de.jalumu.magma.platform.base.platform.util.SplashScreen;
import net.kyori.adventure.platform.bungeecord.BungeeAudiences;
import net.md_5.bungee.api.plugin.Plugin;

@BungeecordPlugin(name = "MagmaKT-Bungee", version = "0.0.1", description = "MagmaKT for Bungeecord", author = "JaLuMu", dependsPlugin = {})
public class MagmaPaperBootstrap extends Plugin implements MagmaPlatform {

    private BungeeAudiences adventure;

    public BungeeAudiences adventure() {
        if (this.adventure == null) {
            throw new IllegalStateException("Cannot retrieve audience provider while plugin is not enabled");
        }
        return this.adventure;
    }

    @Override
    public void onEnable() {
        this.adventure = BungeeAudiences.create(this);
        SplashScreen.splashScreen(this);
    }

    @Override
    public void onDisable() {
        if (this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
    }

    @Override
    public String getName() {
        return getDescription().getName();
    }

    @Override
    public String getVersion() {
        return getDescription().getVersion();
    }

    @Override
    public MagmaPlatformType getPlatformType() {
        return MagmaPlatformType.PROXY;
    }

    @Override
    public String getPlatformName() {
        return getProxy().getName();
    }

    @Override
    public String getPlatformVersion() {
        return getProxy().getVersion();
    }
}
