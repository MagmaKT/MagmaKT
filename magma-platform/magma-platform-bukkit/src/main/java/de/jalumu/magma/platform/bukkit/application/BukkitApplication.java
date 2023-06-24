package de.jalumu.magma.platform.bukkit.application;

import de.exlll.configlib.ConfigLib;
import de.exlll.configlib.YamlConfigurationProperties;
import de.jalumu.magma.platform.base.application.MagmaApplicationBase;
import de.jalumu.magma.platform.bukkit.command.MagmaBukkitCommandAnnotationReplacer;
import net.kyori.adventure.platform.AudienceProvider;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;
import revxrsal.commands.CommandHandler;
import revxrsal.commands.bukkit.BukkitCommandHandler;

import java.io.File;

public abstract class BukkitApplication extends JavaPlugin implements MagmaApplicationBase {

    private CommandHandler commandHandler;

    private BukkitAudiences adventure;

    private YamlConfigurationProperties.Builder configurationProperties;


    private BukkitAudiences adventure() {
        if (this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return this.adventure;
    }

    @Override
    public void onLoad() {

        File configFolder = getDataFolder();

        if (!configFolder.exists()) {
            configFolder.mkdirs();
        }

        initialize();
    }

    @Override
    public void onEnable() {
        this.adventure = BukkitAudiences.create(this);
        configurationProperties = ConfigLib.BUKKIT_DEFAULT_PROPERTIES.toBuilder();

        commandHandler = BukkitCommandHandler.create(this);
        commandHandler.registerAnnotationReplacer(de.jalumu.magma.command.MagmaCommand.class, new MagmaBukkitCommandAnnotationReplacer());
        start();
    }

    @Override
    public void onDisable() {
        shutdown();
        if (this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
    }

    @Override
    public AudienceProvider getAudience() {
        return adventure();
    }

    @Override
    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public YamlConfigurationProperties.Builder getConfigurationProperties() {
        return configurationProperties;
    }
}
