package de.jalumu.magma.platform.bungee.bootstrap;

import de.exlll.configlib.YamlConfigurations;
import de.jalumu.magma.annotation.bungee.platform.application.BungeecordPlugin;
import de.jalumu.magma.platform.MagmaPlatform;
import de.jalumu.magma.platform.MagmaPlatformProvider;
import de.jalumu.magma.platform.MagmaPlatformType;
import de.jalumu.magma.platform.ServerImplementation;
import de.jalumu.magma.platform.base.config.ServerIdConfig;
import de.jalumu.magma.platform.base.module.ModuleLoader;
import de.jalumu.magma.platform.base.platform.util.SplashScreen;
import de.jalumu.magma.platform.bungee.module.BungeeModuleLoader;
import de.jalumu.magma.platform.bungee.player.BungeePlayerProvider;
import de.jalumu.magma.player.PlayerProvider;
import net.kyori.adventure.platform.bungeecord.BungeeAudiences;
import net.md_5.bungee.api.plugin.Plugin;
import revxrsal.commands.CommandHandler;

import java.io.File;

@BungeecordPlugin(name = "MagmaKT-Bungee", version = "0.0.1", description = "MagmaKT for Bungeecord", author = "JaLuMu", dependsPlugin = {})
public class MagmaBungeeBootstrap extends Plugin implements MagmaPlatform {

    private BungeeAudiences adventure;

    private ModuleLoader moduleLoader;

    private ServerIdConfig serverIdConfig;

    private String serverID;

    public BungeeAudiences adventure() {
        if (this.adventure == null) {
            throw new IllegalStateException("Cannot retrieve audience provider while plugin is not enabled");
        }
        return this.adventure;
    }

    @Override
    public void onLoad() {
        MagmaPlatformProvider.setPlatform(this);
    }

    @Override
    public void onEnable() {
        this.adventure = BungeeAudiences.create(this);

        File serverIdFile = new File(getDataFolder(), "serverID.yml");

        if (serverIdFile.exists()) {
            serverIdConfig = YamlConfigurations.load(serverIdFile.toPath(), ServerIdConfig.class);
        } else {
            serverIdConfig = new ServerIdConfig("Proxy");
            YamlConfigurations.save(serverIdFile.toPath(), ServerIdConfig.class, serverIdConfig);
        }

        serverID = serverIdConfig.getServerID();

        PlayerProvider.setProvider(new BungeePlayerProvider(this));

        moduleLoader = new BungeeModuleLoader(this, new File(this.getDataFolder().toPath() + File.separator + "modules"));
        moduleLoader.prepare();

        moduleLoader.loadModules();
        moduleLoader.enableCompatibleModules();

        SplashScreen.splashScreen(this);

    }

    @Override
    public void onDisable() {
        if (this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
        moduleLoader.disableModules();
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
    public String getServerID() {
        return serverID;
    }

    @Override
    public void setServerID(String serverID) {
        this.serverID = serverID;
    }

    @Override
    public CommandHandler getCommandHandler() {
        return null;
    }

    @Override
    public Object getMagmaPluginInstance() {
        return this;
    }

}
