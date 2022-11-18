package de.keeeks.magma.core.placeholders;

import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

import java.util.UUID;

public final class Placeholders {

    public static TagResolver getPlayer(String playerName, UUID uuid) {
        return TagResolver.builder()
                .resolver(Placeholder.parsed("player_name", playerName))
                .resolver(Placeholder.parsed("player_uuid", uuid.toString()))
                .resolver(Placeholder.parsed("rank_displayname", getPlayerGroupName(uuid)))
                .resolver(Placeholder.parsed("player_prefix", getPlayerPrefix(uuid)))
                .resolver(Placeholder.parsed("player_first_prefix_color", getPlayerPrefixColor(uuid)))
                .build();
    }

    private static String getPlayerPrefix(UUID playerUniqueId) {
        LuckPerms api = LuckPermsProvider.get();
        var user = api.getUserManager().getUser(playerUniqueId);
        if (user == null) {
            return "Unknown";
        }
        return user.getCachedData().getMetaData().getPrefix();
    }

    private static String getPlayerGroupName(UUID playerUniqueId) {
        LuckPerms api = LuckPermsProvider.get();
        var user = api.getUserManager().getUser(playerUniqueId);
        if (user == null) {
            return "Unknown";
        }
        String playerPrimaryGroupName = user.getCachedData().getMetaData().getPrimaryGroup();
        if (playerPrimaryGroupName == null) {
            return "Unknown";
        }
        var group = api.getGroupManager().getGroup(playerPrimaryGroupName);
        if (group == null) {
            return "Unknown";
        }
        return group.getDisplayName();
    }

    private static String getPlayerPrefixColor(UUID playerUniqueId) {
        String prefix = getPlayerPrefix(playerUniqueId);
        String[] split = prefix.split(">");
        return split[0] + ">";
    }
}