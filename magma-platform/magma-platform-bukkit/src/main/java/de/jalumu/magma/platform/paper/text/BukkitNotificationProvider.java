package de.jalumu.magma.platform.paper.text;

import de.jalumu.magma.platform.base.text.Notification;
import de.jalumu.magma.platform.base.text.NotificationProvider;

public class BukkitNotificationProvider extends NotificationProvider {


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
