package de.jalumu.magma.text;

import net.kyori.adventure.text.Component;

import java.util.Locale;
import java.util.Map;

/**
 * A translatable Text with support for different parser
 */
public interface Text {

    static Text text(String text) {
        return TextProvider.getProvider().text(text);
    }

    Text setText(String text);

    Text addTranslation(Locale language, String text);

    boolean parseColorCodes();

    Text parseColorCodes(boolean b);

    Text parsePlaceholders(boolean b);

    boolean parsePlaceholders();

    Text allowMiddleware(boolean b);

    boolean allowMiddleware();

    String getDefaultText();

    String getTranslation(Locale language);

    String getTranslationOrDefault(Locale language);

    Component getDefaultComponent();

    Component getTranslatedComponent(Locale language);

    Map<Locale,String> getTranslations();

}
