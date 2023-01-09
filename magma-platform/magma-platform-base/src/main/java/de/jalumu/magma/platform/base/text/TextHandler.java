package de.jalumu.magma.platform.base.text;

import de.exlll.configlib.Configuration;
import de.jalumu.magma.text.Text;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TextHandler {

    private HashMap<String, Text> translationList = new HashMap<>();

    public void addText(String key, Text text) {
        translationList.put(key, text);
    }

    public Text getText(String key) {
        return translationList.get(key);
    }

    public String getText(String key, String langCode) {
        Text text = getText(key);
        return text.getTranslation(langCode);
    }


}
