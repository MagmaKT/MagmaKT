package de.jalumu.magma.platform.base.text;

import de.jalumu.magma.text.Text;
import de.jalumu.magma.text.placeholder.Placeholders;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

import java.util.*;

public class TextBaseImp implements Text {

    private String text = "";

    private HashMap<Locale, String> translations = new HashMap<>();

    private boolean colorCodes = true;
    private boolean placeholders = true;
    private boolean middleware = true;

    @Override
    public Text setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public Text addTranslation(Locale language, String text) {
        translations.put(language, text);
        return this;
    }

    @Override
    public boolean parseColorCodes() {
        return colorCodes;
    }

    @Override
    public Text parseColorCodes(boolean b) {
        colorCodes = b;
        return this;
    }

    @Override
    public Text parsePlaceholders(boolean b) {
        placeholders = b;
        return this;
    }

    @Override
    public boolean parsePlaceholders() {
        return placeholders;
    }

    @Override
    public Text allowMiddleware(boolean b) {
        middleware = b;
        return this;
    }

    @Override
    public boolean allowMiddleware() {
        return middleware;
    }

    @Override
    public String getDefaultText() {
        return text;
    }

    @Override
    public String getTranslation(Locale language) {
        return translations.get(language);
    }

    @Override
    public String getTranslationOrDefault(Locale language) {
        return translations.getOrDefault(language, text);
    }

    @Override
    public Component getDefaultComponent() {
        return miniMessage().deserialize(text);
    }

    @Override
    public Component getTranslatedComponent(Locale language) {
        return miniMessage().deserialize(getTranslationOrDefault(language));
    }

    @Override
    public Map<Locale, String> getTranslations() {
        return translations;
    }

    private MiniMessage miniMessage() {
        MiniMessage.Builder miniBuilder = MiniMessage.builder();
        TagResolver.Builder tagBuilder = TagResolver.builder();

        List<TagResolver> resolvers = new ArrayList<>();

        if (colorCodes) {
            resolvers.add(TagResolver.standard());
        }

        if (placeholders) {
            resolvers.add(Placeholders.global());
        }

        TagResolver tagResolver = tagBuilder.resolvers(resolvers).build();
        MiniMessage miniMessage = miniBuilder.tags(tagResolver).build();
        return miniMessage;
    }

}
