package de.jalumu.magma.platform.bungee.application;

import de.exlll.configlib.YamlConfigurationProperties;
import de.jalumu.magma.platform.base.application.MagmaApplicationBase;
import de.jalumu.magma.platform.bungee.command.MagmaBungeeCommandAnnotationReplacer;
import de.jalumu.magma.platform.bungee.text.notification.BungeeNotificationProvider;
import de.jalumu.magma.text.notification.NotificationProvider;
import net.md_5.bungee.api.plugin.Plugin;
import revxrsal.commands.CommandHandler;
import revxrsal.commands.bungee.BungeeCommandHandler;

import java.io.File;

public abstract class BungeeApplication extends Plugin implements MagmaApplicationBase {

    private CommandHandler commandHandler;

    private YamlConfigurationProperties.Builder configurationProperties;

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
        configurationProperties = YamlConfigurationProperties.newBuilder();

        commandHandler = BungeeCommandHandler.create(this);
        commandHandler.registerAnnotationReplacer(de.jalumu.magma.command.MagmaCommand.class, new MagmaBungeeCommandAnnotationReplacer());

        start();
    }

    @Override
    public void onDisable() {
        shutdown();
    }

    @Override
    public CommandHandler getCommandHandler() {
        return commandHandler;
    }
}
