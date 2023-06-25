package de.jalumu.magma.platform.bukkit.text.notification

import de.jalumu.magma.platform.base.text.notification.NotificationBaseImp
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound

class BukkitNotification : NotificationBaseImp() {
    private var templateString: String? = null
    private var soundString: String? = null
    fun setNotificationTemplate(template: String?) {
        templateString = template
    }

    fun setDefaultSound(sound: String?) {
        soundString = sound
    }

    override fun getNotificationTemplate(): String {
        return templateString!!
    }

    override fun getDefaultSound(): Sound {
        return Sound.sound(Key.key(soundString!!, ':'), Sound.Source.MASTER, 1f, 1f)
    }
}