package de.jalumu.magma.platform.bungee.text

import de.jalumu.magma.text.Text
import de.jalumu.magma.text.TextProvider

class BungeeTextProvider : TextProvider() {
    override fun text(text: String): Text {
        return BungeeText().setText(text)
    }
}
