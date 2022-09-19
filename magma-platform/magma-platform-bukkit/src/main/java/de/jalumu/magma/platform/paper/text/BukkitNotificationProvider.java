package de.jalumu.magma.platform.paper.text;

import de.jalumu.magma.platform.base.text.Notification;
import de.jalumu.magma.platform.base.text.NotificationProvider;
import de.jalumu.magma.platform.paper.bootstrap.MagmaPaperBootstrap;
import org.bukkit.Sound;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class BukkitNotificationProvider extends NotificationProvider {

    private MagmaPaperBootstrap magma;
    private YamlConfiguration configuration;

    public BukkitNotificationProvider(MagmaPaperBootstrap magma){
        this.magma = magma;
        File config = new File(magma.getDataFolder(),"notification.yml");
        configuration = new YamlConfiguration();

        if (!config.exists()) {
            try {
                config.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            configuration.load(config);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }

        configuration.addDefault("notification.global.debug.template","<dark_aqua>DEBUG <dark_gray>| <gray><notification_text>");
        configuration.addDefault("notification.global.debug.sound", Sound.UI_BUTTON_CLICK.key().value());

        configuration.addDefault("notification.global.info.template","<green>INFO <dark_gray>| <dark_green><notification_text>");
        configuration.addDefault("notification.global.info.sound", Sound.BLOCK_NOTE_BLOCK_CHIME.key().value());

        configuration.addDefault("notification.global.success.template","<green>SUCCESS <dark_gray>| <green><notification_text>");
        configuration.addDefault("notification.global.success.sound",org.bukkit.Sound.ENTITY_PLAYER_LEVELUP.key().value());

        configuration.addDefault("notification.global.warning.template","<yellow>WARNING <dark_gray>| <green><notification_text>");
        configuration.addDefault("notification.global.warning.sound", Sound.BLOCK_NOTE_BLOCK_BASS.key().value());

        configuration.addDefault("notification.global.error.template","<red>Error <dark_gray>| <green><notification_text>");
        configuration.addDefault("notification.global.error.sound", Sound.ITEM_SHIELD_BREAK.key().value());

        configuration.options().copyDefaults(true);

        try {
            configuration.save(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Notification getDebug() {
       return new BukkitNotification();
    }

    @Override
    protected Notification getInfo() {
        return new BukkitNotification();
    }

    @Override
    protected Notification getSuccess() {
        return new BukkitNotification();
    }

    @Override
    protected Notification getWarning() {
        return new BukkitNotification();
    }

    @Override
    protected Notification getError() {
        return new BukkitNotification();
    }
}
