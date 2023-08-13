package de.jalumu.magma.player;

import java.util.UUID;

/**
 * A helper class to get @{@link MagmaPlayer}
 */
public interface Players {

    static FetchedPlayer fetchPlayer(String name) {
        return PlayerProvider.getProvider().fetchPlayer(name);
    }

    static FetchedPlayer fetchPlayer(UUID uuid) {
        return PlayerProvider.getProvider().fetchPlayer(uuid);
    }

    static FetchedPlayer getCachedPlayer(String name) {
        return PlayerProvider.getProvider().getCachedPlayer(name);
    }

    static FetchedPlayer getCachedPlayer(UUID uuid) {
        return PlayerProvider.getProvider().getCachedPlayer(uuid);
    }

    static MagmaPlayer getOnlinePlayer(String name) {
        return PlayerProvider.getProvider().getOnlinePlayer(name);
    }

    static MagmaPlayer getOnlinePlayer(UUID uuid) {
        return PlayerProvider.getProvider().getOnlinePlayer(uuid);
    }

    static MagmaPlayer[] getOnlinePlayers() {
        return PlayerProvider.getProvider().getOnlinePlayers();
    }

}
