package de.jalumu.magma.player;

import net.kyori.adventure.audience.Audience;

/**
 * A crossplatform player implementation for Magma
 */
public interface MagmaPlayer extends FetchedPlayer {

    String getDisplayName();

    public Audience getAudience();

}
