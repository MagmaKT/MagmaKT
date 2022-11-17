package de.jalumu.magma.platform.bungee.bootstrap;

import de.jalumu.magma.annotation.bungee.platform.application.BungeecordPlugin;
import de.jalumu.magma.platform.base.module.ModuleLoader;
import de.jalumu.magma.platform.base.platform.MagmaPlatform;
import de.jalumu.magma.platform.base.platform.MagmaPlatformType;
import de.jalumu.magma.platform.base.platform.ServerImplementation;
import de.jalumu.magma.platform.base.platform.util.SplashScreen;
import de.jalumu.magma.platform.bungee.module.BungeeModuleLoader;
import net.kyori.adventure.platform.bungeecord.BungeeAudiences;
import net.md_5.bungee.api.plugin.Plugin;

@BungeecordPlugin(name = "MagmaKT-Bungee", version = "0.0.1", description = "MagmaKT for Bungeecord", author = "JaLuMu", dependsPlugin = {})
public class MagmaBungeeBootstrap extends Plugin implements MagmaPlatform {

    private BungeeAudiences adventure;

    private BungeeModuleLoader moduleLoader;

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
        moduleLoader = new BungeeModuleLoader(this);

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
    public ServerImplementation getServerImplementation() {
        return ServerImplementation.BUNGEECORD;
    }

    @Override
    public String getPlatformName() {
        return getProxy().getName();
    }

    @Override
    public String getPlatformVersion() {
        return getProxy().getVersion();
    }

    @Override
    public Object getMagmaPluginInstance() {
        return this;
    }

    @Override
    public ModuleLoader getModuleLoader() {
        return null;
    }
}
