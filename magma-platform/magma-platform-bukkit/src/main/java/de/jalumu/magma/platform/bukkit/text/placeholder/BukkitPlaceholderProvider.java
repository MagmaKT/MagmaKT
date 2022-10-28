package de.jalumu.magma.platform.bukkit.text.placeholder;

import de.jalumu.magma.platform.base.text.placeholder.PlaceholderProvider;
import de.jalumu.magma.platform.bukkit.bootstrap.MagmaBukkitBootstrap;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BukkitPlaceholderProvider extends PlaceholderProvider {

    private MagmaBukkitBootstrap magma;

    public BukkitPlaceholderProvider(MagmaBukkitBootstrap magma) {
        this.magma = magma;
    }

    @Override
    protected TagResolver getGlobal() {
        return TagResolver.builder()
                .resolver(Placeholder.parsed("local_player_online", String.valueOf(Bukkit.getOnlinePlayers().size())))
                .build();
    }

    @Override
    protected TagResolver getPlayer(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        return TagResolver.builder()
                .resolver(getGlobal())
                .resolver(Placeholder.parsed("bungee_player_online", PlaceholderAPI.setPlaceholders(player, "%bungee_total%")))
                .resolver(Placeholder.parsed("player_name", player.getName()))
                .resolver(Placeholder.parsed("player_uuid", uuid.toString()))
                .resolver(Placeholder.parsed("rank_displayname", getPlayerGroupName(player)))
                .resolver(Placeholder.parsed("player_prefix", getPlayerPrefix(player)))
                .resolver(Placeholder.parsed("player_first_prefix_color", getPlayerPrefixColor(player)))
                .build();
    }

    private String getPlayerPrefix(Player player) {
        LuckPerms api = LuckPermsProvider.get();
        return api.getPlayerAdapter(Player.class).getUser(player).getCachedData().getMetaData().getPrefix();
    }

    private String getPlayerGroupName(Player player) {
        LuckPerms api = LuckPermsProvider.get();
        String group = api.getPlayerAdapter(Player.class).getUser(player).getCachedData().getMetaData().getPrimaryGroup();
        return api.getGroupManager().getGroup(group).getDisplayName();
    }

    private String getPlayerPrefixColor(Player player) {
        String prefix = getPlayerPrefix(player);
        String[] split = prefix.split(">");
        return split[0] + ">";
    }

}
