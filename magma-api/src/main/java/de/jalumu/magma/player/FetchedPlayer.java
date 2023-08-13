package de.jalumu.magma.player;

import java.util.UUID;

/**
 * A wrapper for fetched Player data
 */
public interface FetchedPlayer {

    String getName();

    UUID getUniqueId();

    boolean isLegacy();

    boolean isOnline();

}
