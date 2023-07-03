package de.jalumu.magma.platform.bungee.player

import de.jalumu.magma.platform.base.player.BasePlayerProvider
import de.jalumu.magma.platform.bungee.bootstrap.MagmaBungeeBootstrap
import de.jalumu.magma.player.MagmaPlayer
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.connection.ProxiedPlayer
import java.util.*

class BungeePlayerProvider(private val bootstrap: MagmaBungeeBootstrap) : BasePlayerProvider() {
    override fun getOnlinePlayer(name: String): MagmaPlayer {
        val player = ProxyServer.getInstance().getPlayer(name)
        return BungeePlayer(bootstrap, player)
    }

    override fun getOnlinePlayer(uuid: UUID): MagmaPlayer {
        val player = ProxyServer.getInstance().getPlayer(uuid)
        return BungeePlayer(bootstrap, player)
    }

    override fun getOnlinePlayers(): Array<MagmaPlayer> {
        return ProxyServer.getInstance().players.stream().map<BungeePlayer> { player: ProxiedPlayer? ->
            BungeePlayer(
                bootstrap, player!!
            )
        }.toArray() as Array<MagmaPlayer>
    }
}
