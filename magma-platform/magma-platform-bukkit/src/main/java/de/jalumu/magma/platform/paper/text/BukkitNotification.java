package de.jalumu.magma.platform.paper.text;

import de.jalumu.magma.platform.base.text.NotificationBaseImp;
import net.kyori.adventure.sound.Sound;

public class BukkitNotification extends NotificationBaseImp {

    @Override
    public String getNotificationTemplate() {
        return "ASDF | <notification_text>";
    }

    @Override
    public Sound getDefaultSound() {
        return Sound.sound((org.bukkit.Sound.ENTITY_PLAYER_LEVELUP.key()), Sound.Source.MASTER,1f,1f);
    }

}
