package de.jalumu.magma.platform.base.config.serializer;

import de.exlll.configlib.Serializer;
import de.jalumu.magma.text.notification.Notification;

import java.util.HashMap;

public class NotificationSerializer implements Serializer<Notification, HashMap<String,Object>> {
    @Override
    public HashMap<String, Object> serialize(Notification element) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("template",element.getNotificationTemplate());
        map.put("sound",element.getDefaultSound().name().namespace() + ":" + element.getDefaultSound().name().value());
        return map;
    }

    @Override
    public Notification deserialize(HashMap<String, Object> element) {
        return Notification.raw(element.get("template").toString(),element.get("sound").toString());
    }
}
