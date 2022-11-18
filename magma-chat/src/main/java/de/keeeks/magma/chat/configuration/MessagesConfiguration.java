package de.keeeks.magma.chat.configuration;

public record MessagesConfiguration(
        String chatFormat,
        String joinFormat,
        String leaveFormat,
        boolean joinQuitMessagesEnabled
) {

    public static MessagesConfiguration createDefault() {
        return new MessagesConfiguration(
                "<rank_displayname> <dark_gray>| <player_first_prefix_color><player_name> " +
                        "<dark_gray>-<reset> <message>",
                "<dark_gray>[<green>+<dark_gray>] <rank_displayname> <dark_gray>| " +
                        "<player_first_prefix_color><player_name>",
                        "<dark_gray>[<red>-<dark_gray>] <rank_displayname> <dark_gray>| " +
                                "<player_first_prefix_color><player_name>",
                false
        );
    }
}