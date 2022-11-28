package de.jalumu.magma.platform.bukkit.player;

import de.jalumu.magma.player.FetchedPlayer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class BukkitFetchedPlayer implements FetchedPlayer {

    private OfflinePlayer player;

    public BukkitFetchedPlayer(OfflinePlayer player) {
        this.player = player;
    }

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public UUID getUniqueId() {
        return player.getUniqueId();
    }

    @Override
    public boolean isLegacy() {
        return false;
    }

    @Override
    public boolean isOnline() {
        return Bukkit.getOnlinePlayers().stream().anyMatch(onlinePlayer -> onlinePlayer.getUniqueId().equals(player.getUniqueId()));
    }
}
