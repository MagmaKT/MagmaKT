package de.jalumu.magma.platform.bungee.application;

import de.exlll.configlib.YamlConfigurationProperties;
import de.jalumu.magma.platform.base.application.MagmaApplicationBase;
import de.jalumu.magma.platform.bungee.command.MagmaBungeeCommandAnnotationReplacer;
import net.kyori.adventure.platform.AudienceProvider;
import net.kyori.adventure.platform.bungeecord.BungeeAudiences;
import net.md_5.bungee.api.plugin.Plugin;
import revxrsal.commands.CommandHandler;
import revxrsal.commands.bungee.BungeeCommandHandler;

import java.io.File;

public abstract class BungeeApplication extends Plugin implements MagmaApplicationBase {

    private CommandHandler commandHandler;

    private BungeeAudiences adventure;

    private YamlConfigurationProperties.Builder configurationProperties;


    private BungeeAudiences adventure() {
        if (this.adventure == null) {
            throw new IllegalStateException("Cannot retrieve audience provider while plugin is not enabled");
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

        this.adventure = BungeeAudiences.create(this);

        configurationProperties = YamlConfigurationProperties.newBuilder();

        commandHandler = BungeeCommandHandler.create(this);
        commandHandler.registerAnnotationReplacer(de.jalumu.magma.command.MagmaCommand.class, new MagmaBungeeCommandAnnotationReplacer());

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
}

