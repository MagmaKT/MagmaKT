package de.jalumu.magma.platform.paper.text.placeholder;

import de.jalumu.magma.platform.base.text.placeholder.PlaceholderProvider;
import de.jalumu.magma.platform.base.text.placeholder.Placeholders;
import de.jalumu.magma.platform.paper.bootstrap.MagmaPaperBootstrap;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.clip.placeholderapi.expansion.manager.LocalExpansionManager;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BukkitPlaceholderProvider extends PlaceholderProvider {

    private MagmaPaperBootstrap magma;

    public BukkitPlaceholderProvider(MagmaPaperBootstrap magma) {
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
                .resolver(Placeholder.parsed("bungee_player_online", PlaceholderAPI.setPlaceholders(player,"%bungee_total%")))
                .resolver(Placeholder.parsed("player_name", player.getName()))
                .resolver(Placeholder.parsed("player_uuid", uuid.toString()))
                .resolver(Placeholder.parsed("rank_displayname", magma.getPerms().getPrimaryGroup(player)))
                .build();
    }

}
