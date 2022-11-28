package de.jalumu.magma.platform.bungee.player;

import de.jalumu.magma.platform.base.player.BasePlayerProvider;
import de.jalumu.magma.player.FetchedPlayer;
import de.jalumu.magma.player.MagmaPlayer;
import de.jalumu.magma.player.PlayerProvider;
import net.md_5.bungee.api.ProxyServer;

import java.util.UUID;

public class BungeePlayerProvider extends BasePlayerProvider {

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
