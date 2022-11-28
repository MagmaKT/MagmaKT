package de.jalumu.magma.platform.bukkit.player;

import de.jalumu.magma.platform.base.player.BasePlayerProvider;
import de.jalumu.magma.platform.bukkit.bootstrap.MagmaBukkitBootstrap;
import de.jalumu.magma.player.FetchedPlayer;
import de.jalumu.magma.player.MagmaPlayer;
import de.jalumu.magma.player.PlayerProvider;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class BukkitPlayerProvider extends BasePlayerProvider {

    public BukkitPlayerProvider(MagmaBukkitBootstrap bukkitBootstrap) {
        super();
    }

    @Override
    protected FetchedPlayer getCachedPlayer(String name) {
        return fetchPlayer(name);
    }

    @Override
    protected FetchedPlayer getCachedPlayer(UUID uuid) {
        return fetchPlayer(uuid);
    }

    @Override
    protected MagmaPlayer getOnlinePlayer(String name) {
        return null;
    }

    @Override
    protected MagmaPlayer getOnlinePlayer(UUID uuid) {
        return null;
    }

    @Override
    protected MagmaPlayer[] getOnlinePlayers() {
        return new MagmaPlayer[0];
    }
}
