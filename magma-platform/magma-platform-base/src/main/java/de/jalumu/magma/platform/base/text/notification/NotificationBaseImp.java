package de.jalumu.magma.platform.base.text.notification;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public abstract class NotificationBaseImp implements Notification{

    private Component text;
    private Sound sound;

    @Override
    public Component getNotificationText() {
        return text;
    }

    @Override
    public Notification setNotificationText(Component component) {
        text = component;
        return this;
    }

    @Override
    public Notification setNotificationText(String text) {
        this.text = MiniMessage.miniMessage().deserialize(text);
        return this;
    }

    @Override
    public Notification setNotificationSound(Sound sound) {
        this.sound = sound;
        return this;
    }

    @Override
    public Notification setNotificationSound(String sound) {
        this.sound = Sound.sound(Key.key(sound), Sound.Source.MASTER,1f,1f);
        return this;
    }

    @Override
    public Notification setNotificationSound(Key key) {
        this.sound = Sound.sound(key, Sound.Source.MASTER,1f,1f);
        return this;
    }

    @Override
    public void send(Audience audience) {
        Component parsed = MiniMessage.miniMessage().deserialize(getNotificationTemplate(), Placeholder.component("notification_text", text));
        audience.sendMessage(parsed);
        if (sound != null){
            audience.playSound(sound);
        }else{
            audience.playSound(getDefaultSound());
        }
    }

}
