package de.jalumu.magma.platform.base.config.serializer

import de.exlll.configlib.Serializer
import de.jalumu.magma.text.notification.Notification

class NotificationSerializer : Serializer<Notification, HashMap<String, Any>> {
    override fun serialize(element: Notification): HashMap<String, Any> {
        val map = HashMap<String, Any>()
        map["template"] = element.notificationTemplate
        map["sound"] = element.defaultSound.name().namespace() + ":" + element.defaultSound.name().value()
        return map
    }

    override fun deserialize(element: HashMap<String, Any>): Notification {
        return Notification.raw(element["template"].toString(), element["sound"].toString())
    }
}
