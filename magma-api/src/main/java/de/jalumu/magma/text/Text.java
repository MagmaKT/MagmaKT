package de.jalumu.magma.text;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class Text {

    @Comment("Default / Fallback Text")
    private @NonNull String text;

    @Comment("Mapping of all other Languages")
    private HashMap<String, String> translations = new HashMap<>();

    public Text addTranslation(String langCode, String text) {
        translations.put(langCode, text);
        return this;
    }

    public String getTranslation(String langCode) {
        return translations.getOrDefault(langCode, text);
    }

}
