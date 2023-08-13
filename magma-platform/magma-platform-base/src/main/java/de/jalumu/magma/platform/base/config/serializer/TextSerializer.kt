package de.jalumu.magma.platform.base.config.serializer

import de.exlll.configlib.Serializer
import de.jalumu.magma.text.Text
import java.util.*

class TextSerializer : Serializer<Text?, HashMap<String, Any>> {
    override fun serialize(element: Text?): HashMap<String, Any> {
        val map = HashMap<String, Any>()
        map["settings.parseColorCodes"] = element!!.parseColorCodes()
        map["settings.parsePlaceholders"] = element.parsePlaceholders()
        map["settings.allowMiddleware"] = element.allowMiddleware()
        map["text.default"] = element.defaultText
        element.translations.forEach { (locale: Locale, s: String) -> map["text.lang." + locale.isO3Country] = s }
        map["text.default"] = element.defaultText
        return map
    }

    override fun deserialize(element: HashMap<String, Any>): Text? {
        return null
    }
}
