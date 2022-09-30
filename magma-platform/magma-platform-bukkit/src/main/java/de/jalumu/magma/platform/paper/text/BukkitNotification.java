package de.jalumu.magma.platform.paper.text;

import de.jalumu.magma.platform.base.text.notification.NotificationBaseImp;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;

public class BukkitNotification extends NotificationBaseImp {

    private String templateString;
    private String soundString;


    protected void setNotificationTemplate(String template){
        templateString = template;
    }

    protected void setDefaultSound(String sound){
        this.soundString = sound;
    }

    @Override
    public String getNotificationTemplate() {
        return templateString;
    }

    @Override
    public Sound getDefaultSound() {
        return Sound.sound(Key.key(soundString,':'), Sound.Source.MASTER,1f,1f);
    }

}
