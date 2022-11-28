package de.jalumu.magma.platform.bukkit.player;

import de.jalumu.magma.platform.base.player.BasePlayerProvider;
import de.jalumu.magma.platform.bukkit.bootstrap.MagmaBukkitBootstrap;
import de.jalumu.magma.player.MagmaPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BukkitPlayerProvider extends BasePlayerProvider {

    private MagmaBukkitBootstrap magma;

    public BukkitPlayerProvider(MagmaBukkitBootstrap bukkitBootstrap) {
        magma = bukkitBootstrap;
    }

    @Override
    protected MagmaPlayer getOnlinePlayer(String name) {
        Player player = magma.getServer().getPlayer(name);
        return new BukkitPlayer(magma, player);
    }

    @Override
    protected MagmaPlayer getOnlinePlayer(UUID uuid) {
        Player player = magma.getServer().getPlayer(uuid);
        return new BukkitPlayer(magma, player);
    }

    @Override
    protected MagmaPlayer[] getOnlinePlayers() {
        return magma.getServer().getOnlinePlayers().stream().map(player -> new BukkitPlayer(magma, player)).toArray(MagmaPlayer[]::new);
    }
}
