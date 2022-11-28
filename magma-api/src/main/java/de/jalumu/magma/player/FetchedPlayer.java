package de.jalumu.magma.player;

import java.util.UUID;

public interface FetchedPlayer {

    String getName();

    UUID getUniqueId();

    boolean isLegacy();

    boolean isOnline();

}
