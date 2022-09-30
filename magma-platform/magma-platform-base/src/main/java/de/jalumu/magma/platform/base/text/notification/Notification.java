package de.jalumu.magma.platform.base.text.notification;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;

public interface Notification {

    static Notification debug(){
        return NotificationProvider.getProvider().getDebug();
    }

    static Notification debug(String text){
        return NotificationProvider.getProvider().getDebug().setNotificationText(text);
    }

    static Notification info(){
        return NotificationProvider.getProvider().getInfo();
    }

    static Notification info(String text){
        return NotificationProvider.getProvider().getInfo().setNotificationText(text);
    }

    static Notification success(){
        return NotificationProvider.getProvider().getSuccess();
    }

    static Notification success(String text){
        return NotificationProvider.getProvider().getSuccess().setNotificationText(text);
    }

    static Notification warning(){
        return NotificationProvider.getProvider().getWarning();
    }

    static Notification warning(String text){
        return NotificationProvider.getProvider().getWarning().setNotificationText(text);
    }

    static Notification error(){
        return NotificationProvider.getProvider().getError();
    }

    static Notification error(String text){
        return NotificationProvider.getProvider().getError().setNotificationText(text);
    }

    Component getNotificationText();
    Notification setNotificationText(Component component);
    Notification setNotificationText(String text);
    Notification setNotificationSound(Sound sound);
    Notification setNotificationSound(String sound);
    Notification setNotificationSound(Key key);

    String getNotificationTemplate();
    Sound getDefaultSound();
    void send(Audience audience);

}
