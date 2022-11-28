package de.jalumu.magma.player;

import java.util.UUID;

public abstract class PlayerProvider {

    private static PlayerProvider playerProvider = null;

    public static PlayerProvider getProvider() {
        return playerProvider;
    }

    public static void setProvider(PlayerProvider provider) {
        playerProvider = provider;
    }

    protected abstract FetchedPlayer fetchPlayer(String name);

    protected abstract FetchedPlayer fetchPlayer(UUID uuid);

    protected abstract FetchedPlayer getCachedPlayer(String name);

    protected abstract FetchedPlayer getCachedPlayer(UUID uuid);

    protected abstract MagmaPlayer getOnlinePlayer(String name);

    protected abstract MagmaPlayer getOnlinePlayer(UUID uuid);

    protected abstract MagmaPlayer[] getOnlinePlayers();

}
