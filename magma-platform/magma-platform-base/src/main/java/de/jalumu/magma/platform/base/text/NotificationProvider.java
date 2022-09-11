package de.jalumu.magma.platform.base.text;

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


}
