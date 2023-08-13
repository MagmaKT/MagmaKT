package de.jalumu.magma.text.notification;

import net.kyori.adventure.sound.Sound;

/**
 * A Helper class for Notifications
 */
public abstract class NotificationProvider {

    private static NotificationProvider notificationProvider = null;

    public static NotificationProvider getProvider() {
        return notificationProvider;
    }

    public static void setProvider(NotificationProvider provider) {
        notificationProvider = provider;
    }

    protected abstract Notification getDebug();
    protected abstract Notification getInfo();
    protected abstract Notification getSuccess();
    protected abstract Notification getWarning();
    protected abstract Notification getError();
    protected abstract Notification getRaw(String template, String key);


}
