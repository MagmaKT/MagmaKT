package de.jalumu.magma.platform.base.config.serializer;

import de.exlll.configlib.Serializer;
import de.jalumu.magma.text.Text;

import java.util.HashMap;

public class TextSerializer implements Serializer<Text, HashMap<String, Object>> {
    @Override
    public HashMap<String, Object> serialize(Text element) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("settings.parseColorCodes", element.parseColorCodes());
        map.put("settings.parsePlaceholders", element.parsePlaceholders());
        map.put("settings.allowMiddleware", element.allowMiddleware());
        map.put("text.default", element.getDefaultText());

        element.getTranslations().forEach((locale, s) -> {
            map.put("text.lang." + locale.getISO3Country(), s);
        });

        map.put("text.default", element.getDefaultText());

        return map;
    }

    @Override
    public Text deserialize(HashMap<String, Object> element) {
        return null;
    }
}
