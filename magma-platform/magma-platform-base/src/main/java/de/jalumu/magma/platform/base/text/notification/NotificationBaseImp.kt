package de.jalumu.magma.platform.base.text.notification

import de.jalumu.magma.text.notification.Notification
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder

abstract class NotificationBaseImp : Notification {
    private var text: Component? = null
    private var sound: Sound? = null
    override fun getNotificationText(): Component {
        return text!!
    }

    override fun setNotificationText(component: Component): Notification {
        text = component
        return this
    }

    override fun setNotificationText(text: String): Notification {
        this.text = MiniMessage.miniMessage().deserialize(text)
        return this
    }

    override fun setNotificationSound(sound: Sound): Notification {
        this.sound = sound
        return this
    }

    override fun setNotificationSound(sound: String): Notification {
        this.sound = Sound.sound(Key.key(sound), Sound.Source.MASTER, 1f, 1f)
        return this
    }

    override fun setNotificationSound(key: Key): Notification {
        sound = Sound.sound(key, Sound.Source.MASTER, 1f, 1f)
        return this
    }

    override fun send(audience: Audience) {
        val parsed = MiniMessage.miniMessage()
            .deserialize(notificationTemplate, Placeholder.component("notification_text", text!!))
        audience.sendMessage(parsed)
        if (sound != null) {
            audience.playSound(sound!!)
        } else {
            audience.playSound(defaultSound)
        }
    }
}
