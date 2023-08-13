package de.jalumu.magma.platform.base.text.util

import net.kyori.adventure.text.format.NamedTextColor

object ColorParser {
    @JvmStatic
    fun parseName(color: String?): NamedTextColor {
        return when (color) {
            "black" -> NamedTextColor.BLACK
            "dark_blue" -> NamedTextColor.DARK_BLUE
            "dark_green" -> NamedTextColor.DARK_GREEN
            "dark_aqua" -> NamedTextColor.DARK_AQUA
            "dark_red" -> NamedTextColor.DARK_RED
            "dark_purple" -> NamedTextColor.DARK_PURPLE
            "gold" -> NamedTextColor.GOLD
            "gray" -> NamedTextColor.GRAY
            "dark_gray" -> NamedTextColor.DARK_GRAY
            "blue" -> NamedTextColor.BLUE
            "green" -> NamedTextColor.GREEN
            "aqua" -> NamedTextColor.AQUA
            "red" -> NamedTextColor.RED
            "light_purple" -> NamedTextColor.LIGHT_PURPLE
            "yellow" -> NamedTextColor.YELLOW
            else -> NamedTextColor.WHITE
        }
    }

    @JvmStatic
    fun parseColorCode(color: String?): String {
        return when (color) {
            "&0" -> "black"
            "&1" -> "dark_blue"
            "&2" -> "dark_green"
            "&3" -> "dark_aqua"
            "&4" -> "dark_red"
            "&5" -> "dark_purple"
            "&6" -> "gold"
            "&7" -> "gray"
            "&8" -> "dark_gray"
            "&9" -> "blue"
            "&a" -> "green"
            "&b" -> "aqua"
            "&c" -> "red"
            "&d" -> "light_purple"
            "&e" -> "yellow"
            else -> "white"
        }
    }
}
