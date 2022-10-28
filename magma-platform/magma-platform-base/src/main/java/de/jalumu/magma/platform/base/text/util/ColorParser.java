package de.jalumu.magma.platform.base.text.util;

import net.kyori.adventure.text.format.NamedTextColor;

public class ColorParser {

    public static NamedTextColor parseName(String color) {
        return switch (color) {
            case "black" -> NamedTextColor.BLACK;
            case "dark_blue" -> NamedTextColor.DARK_BLUE;
            case "dark_green" -> NamedTextColor.DARK_GREEN;
            case "dark_aqua" -> NamedTextColor.DARK_AQUA;
            case "dark_red" -> NamedTextColor.DARK_RED;
            case "dark_purple" -> NamedTextColor.DARK_PURPLE;
            case "gold" -> NamedTextColor.GOLD;
            case "gray" -> NamedTextColor.GRAY;
            case "dark_gray" -> NamedTextColor.DARK_GRAY;
            case "blue" -> NamedTextColor.BLUE;
            case "green" -> NamedTextColor.GREEN;
            case "aqua" -> NamedTextColor.AQUA;
            case "red" -> NamedTextColor.RED;
            case "light_purple" -> NamedTextColor.LIGHT_PURPLE;
            case "yellow" -> NamedTextColor.YELLOW;
            default -> NamedTextColor.WHITE;
        };
    }

    public static String parseColorCode(String color) {
        return switch (color) {
            case "&0" -> "black";
            case "&1" -> "dark_blue";
            case "&2" -> "dark_green";
            case "&3" -> "dark_aqua";
            case "&4" -> "dark_red";
            case "&5" -> "dark_purple";
            case "&6" -> "gold";
            case "&7" -> "gray";
            case "&8" -> "dark_gray";
            case "&9" -> "blue";
            case "&a" -> "green";
            case "&b" -> "aqua";
            case "&c" -> "red";
            case "&d" -> "light_purple";
            case "&e" -> "yellow";
            default -> "white";
        };
    }

}
