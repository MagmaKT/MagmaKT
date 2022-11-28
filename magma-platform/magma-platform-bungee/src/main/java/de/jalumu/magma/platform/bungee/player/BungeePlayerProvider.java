package de.jalumu.magma.platform.bungee.player;

import de.jalumu.magma.platform.base.player.BasePlayerProvider;
import de.jalumu.magma.platform.bungee.bootstrap.MagmaBungeeBootstrap;
import de.jalumu.magma.player.MagmaPlayer;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

public class BungeePlayerProvider extends BasePlayerProvider {

    private MagmaBungeeBootstrap bootstrap;

    public BungeePlayerProvider(MagmaBungeeBootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    @Override
    protected MagmaPlayer getOnlinePlayer(String name) {
        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(name);
        return new BungeePlayer(bootstrap, player);
    }

    @Override
    protected MagmaPlayer getOnlinePlayer(UUID uuid) {
        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(uuid);
        return new BungeePlayer(bootstrap, player);
    }

    @Override
    protected MagmaPlayer[] getOnlinePlayers() {
        return ProxyServer.getInstance().getPlayers().stream().map(player -> new BungeePlayer(bootstrap, player)).toArray(MagmaPlayer[]::new);
    }
}
