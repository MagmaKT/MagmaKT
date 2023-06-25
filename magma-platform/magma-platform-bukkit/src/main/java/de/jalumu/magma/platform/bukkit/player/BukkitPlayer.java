package de.jalumu.magma.platform.bukkit.player;

import de.jalumu.magma.platform.bukkit.bootstrap.MagmaBukkitBootstrap;
import de.jalumu.magma.player.MagmaPlayer;
import de.jalumu.magma.text.placeholder.Placeholders;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BukkitPlayer implements MagmaPlayer {

    private MagmaBukkitBootstrap magma;
    private Player player;

    public BukkitPlayer(MagmaBukkitBootstrap magma, Player player) {
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
        return player.isOnline();
    }

    @Override
    public String getDisplayName() {
        return player.getDisplayName();
    }

    @Override
    public Audience getAudience() {
        BukkitAudiences audiences = (BukkitAudiences) magma.getAudience();
        return audiences.player(player);
    }
}
