package de.jalumu.magma.platform.bukkit.text

import de.jalumu.magma.text.Text
import de.jalumu.magma.text.TextProvider

/**
 * The bukkit implementation for the Text api
 */
class BukkitTextProvider : TextProvider() {
    override fun text(text: String): Text {
        return BukkitText().setText(text)
    }
}