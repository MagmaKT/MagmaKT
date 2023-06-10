package de.jalumu.magma.platform.bungee.text.notification;

import de.exlll.configlib.YamlConfigurationProperties;
import de.exlll.configlib.YamlConfigurations;
import de.jalumu.magma.platform.base.config.NotificationConfig;
import de.jalumu.magma.platform.base.config.serializer.NotificationSerializer;
import de.jalumu.magma.platform.bungee.bootstrap.MagmaBungeeBootstrap;
import de.jalumu.magma.text.notification.Notification;
import de.jalumu.magma.text.notification.NotificationProvider;

import java.io.File;
import java.io.IOException;

public class BungeeNotificationProvider extends NotificationProvider {
    private MagmaBungeeBootstrap magma;
    private NotificationConfig notification;

    public BungeeNotificationProvider(MagmaBungeeBootstrap magma) {
        this.magma = magma;
    }

    public void init() {
        File notificationFile = new File(magma.getDataFolder(), "notification.yml");

        YamlConfigurationProperties configurationProperties = YamlConfigurationProperties
                .newBuilder()
                .addSerializer(Notification.class, new NotificationSerializer())
                .createParentDirectories(true)
                .build();

        if (notificationFile.exists()) {
            notification = YamlConfigurations.load(notificationFile.toPath(), NotificationConfig.class,configurationProperties);
        } else {
            notification = new NotificationConfig();
            YamlConfigurations.save(notificationFile.toPath(), NotificationConfig.class, notification,configurationProperties);
        }
    }

    @Override
    protected Notification getDebug() {
        return notification.getDebug();
    }

    @Override
    protected Notification getInfo() {
        return notification.getInfo();
    }

    @Override
    protected Notification getSuccess() {
        return notification.getSuccess();
    }

    @Override
    protected Notification getWarning() {
        return notification.getWarning();
    }

    @Override
    protected Notification getError() {
        return notification.getError();
    }

    @Override
    protected Notification getRaw(String template, String key) {
        BungeeNotification notification = new BungeeNotification();
        notification.setNotificationTemplate(template);
        notification.setDefaultSound(key);
        return notification;
    }
}
