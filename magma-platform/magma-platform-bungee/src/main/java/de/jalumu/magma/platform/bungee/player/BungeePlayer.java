package de.jalumu.magma.platform.bungee.player;

import de.jalumu.magma.platform.bungee.bootstrap.MagmaBungeeBootstrap;
import de.jalumu.magma.player.MagmaPlayer;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.platform.bungeecord.BungeeAudiences;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

public class BungeePlayer implements MagmaPlayer {

    private MagmaBungeeBootstrap magma;
    private ProxiedPlayer player;

    public BungeePlayer(MagmaBungeeBootstrap magma, ProxiedPlayer player) {
        this.magma = magma;
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
        return player.isConnected();
    }

    @Override
    public String getDisplayName() {
        return player.getDisplayName();
    }

    @Override
    public Audience getAudience() {
        BungeeAudiences audiences = (BungeeAudiences) magma.getAudience();
        return audiences.player(player);
    }
}
