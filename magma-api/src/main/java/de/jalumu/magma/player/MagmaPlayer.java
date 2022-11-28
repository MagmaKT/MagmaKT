package de.jalumu.magma.player;

import net.kyori.adventure.audience.Audience;

public interface MagmaPlayer extends FetchedPlayer {

    String getDisplayName();

    public Audience getAudience();

}
