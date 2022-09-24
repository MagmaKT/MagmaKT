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

    public BukkitNotificationProvider(MagmaPaperBootstrap magma) {
        this.magma = magma;
        File config = new File(magma.getDataFolder(), "notification.yml");
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

        configuration.addDefault("notification.global.debug.template", "<dark_aqua>DEBUG <dark_gray>| <gray><notification_text>");
        configuration.addDefault("notification.global.debug.sound", Sound.UI_BUTTON_CLICK.key().asString());

        configuration.addDefault("notification.global.info.template", "<green>INFO <dark_gray>| <dark_green><notification_text>");
        configuration.addDefault("notification.global.info.sound", Sound.BLOCK_NOTE_BLOCK_CHIME.key().asString());

        configuration.addDefault("notification.global.success.template", "<green>SUCCESS <dark_gray>| <green><notification_text>");
        configuration.addDefault("notification.global.success.sound", org.bukkit.Sound.ENTITY_PLAYER_LEVELUP.key().asString());

        configuration.addDefault("notification.global.warning.template", "<yellow>WARNING <dark_gray>| <green><notification_text>");
        configuration.addDefault("notification.global.warning.sound", Sound.BLOCK_NOTE_BLOCK_BASS.key().asString());

        configuration.addDefault("notification.global.error.template", "<red>Error <dark_gray>| <green><notification_text>");
        configuration.addDefault("notification.global.error.sound", Sound.ITEM_SHIELD_BREAK.key().asString());

        configuration.options().copyDefaults(true);

        try {
            configuration.save(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Notification getDebug() {
        String template = configuration.getString("notification.global.debug.template");
        String sound = configuration.getString("notification.global.debug.sound");
        BukkitNotification notification = new BukkitNotification();
        notification.setNotificationTemplate(template);
        notification.setDefaultSound(sound);
        return notification;
    }

    @Override
    protected Notification getInfo() {
        String template = configuration.getString("notification.global.info.template");
        String sound = configuration.getString("notification.global.info.sound");
        BukkitNotification notification = new BukkitNotification();
        notification.setNotificationTemplate(template);
        notification.setDefaultSound(sound);
        return notification;
    }

    @Override
    protected Notification getSuccess() {
        String template = configuration.getString("notification.global.success.template");
        String sound = configuration.getString("notification.global.success.sound");
        BukkitNotification notification = new BukkitNotification();
        notification.setNotificationTemplate(template);
        notification.setDefaultSound(sound);
        return notification;
    }

    @Override
    protected Notification getWarning() {
        String template = configuration.getString("notification.global.warning.template");
        String sound = configuration.getString("notification.global.warning.sound");
        BukkitNotification notification = new BukkitNotification();
        notification.setNotificationTemplate(template);
        notification.setDefaultSound(sound);
        return notification;
    }

    @Override
    protected Notification getError() {
        String template = configuration.getString("notification.global.error.template");
        String sound = configuration.getString("notification.global.error.sound");
        BukkitNotification notification = new BukkitNotification();
        notification.setNotificationTemplate(template);
        notification.setDefaultSound(sound);
        return notification;
    }
}
