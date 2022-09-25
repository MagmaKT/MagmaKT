package de.jalumu.magma.platform.paper.bootstrap;

import de.jalumu.magma.annotation.bukkit.platform.application.BukkitPlugin;
import de.jalumu.magma.module.console.MagmaConsoleModule;
import de.jalumu.magma.module.tablist.MagmaTablistModule;
import de.jalumu.magma.platform.base.module.ModuleLoader;
import de.jalumu.magma.platform.base.platform.MagmaPlatform;
import de.jalumu.magma.platform.base.platform.MagmaPlatformType;
import de.jalumu.magma.platform.base.platform.util.SplashScreen;
import de.jalumu.magma.platform.base.text.NotificationProvider;
import de.jalumu.magma.platform.paper.module.BukkitModuleLoader;
import de.jalumu.magma.platform.paper.text.BukkitNotificationProvider;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.nio.file.Files;

@BukkitPlugin(name = "MagmaKT-Bukkit", version = "0.0.1", description = "MagmaKT for Bukkit", author = "JaLuMu", dependsPlugin = {}, softDependsPlugin = {"Vault"})
public class MagmaPaperBootstrap extends JavaPlugin implements MagmaPlatform {

    private BukkitAudiences adventure;

    private ModuleLoader moduleLoader;

    public BukkitAudiences adventure() {
        if(this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return this.adventure;
    }

    @Override
    public void onLoad() {
        try {
            Files.createDirectories(this.getDataFolder().toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEnable() {

        Metrics metrics = new Metrics(this,16417);

        this.adventure = BukkitAudiences.create(this);
        NotificationProvider.setProvider(new BukkitNotificationProvider(this));

        moduleLoader = new BukkitModuleLoader(this);
        moduleLoader.registerModule(new MagmaConsoleModule());
        moduleLoader.registerModule(new MagmaTablistModule());

        SplashScreen.splashScreen(this);
        moduleLoader.enableModule("Magma-Console");
        moduleLoader.enableModule("Magma-Tablist");

    }

    @Override
    public void onDisable() {
        if(this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
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

    @Override
    public Object getMagmaPluginInstance() {
        return this;
    }

    @Override
    public ModuleLoader getModuleLoader() {
        return moduleLoader;
    }


}
