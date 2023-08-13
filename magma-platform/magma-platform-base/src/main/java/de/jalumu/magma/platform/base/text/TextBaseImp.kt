package de.jalumu.magma.platform.base.text

import de.jalumu.magma.text.Text
import de.jalumu.magma.text.placeholder.Placeholders
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import java.util.*

open class TextBaseImp : Text {
    private var text = ""
    private val translations = HashMap<Locale, String>()
    private var colorCodes = true
    private var placeholders = true
    private var middleware = true
    override fun setText(text: String): Text {
        this.text = text
        return this
    }

    override fun addTranslation(language: Locale, text: String): Text {
        translations[language] = text
        return this
    }

    override fun parseColorCodes(): Boolean {
        return colorCodes
    }

    override fun parseColorCodes(b: Boolean): Text {
        colorCodes = b
        return this
    }

    override fun parsePlaceholders(b: Boolean): Text {
        placeholders = b
        return this
    }

    override fun parsePlaceholders(): Boolean {
        return placeholders
    }

    override fun allowMiddleware(b: Boolean): Text {
        middleware = b
        return this
    }

    override fun allowMiddleware(): Boolean {
        return middleware
    }

    override fun getDefaultText(): String {
        return text
    }

    override fun getTranslation(language: Locale): String {
        return translations[language]!!
    }

    override fun getTranslationOrDefault(language: Locale): String {
        return translations.getOrDefault(language, text)
    }

    override fun getDefaultComponent(): Component {
        return miniMessage().deserialize(text)
    }

    override fun getTranslatedComponent(language: Locale): Component {
        return miniMessage().deserialize(getTranslationOrDefault(language))
    }

    override fun getTranslations(): Map<Locale, String> {
        return translations
    }

    private fun miniMessage(): MiniMessage {
        val miniBuilder =
            MiniMessage.builder()
        val tagBuilder =
            TagResolver.builder()
        val resolvers: MutableList<TagResolver> =
            ArrayList()
        if (colorCodes) {
            resolvers.add(TagResolver.standard())
        }
        if (placeholders) {
            resolvers.add(Placeholders.global())
        }
        val tagResolver =
            tagBuilder.resolvers(resolvers).build()
        return miniBuilder.tags(tagResolver).build()
    }
}
