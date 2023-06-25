package de.jalumu.magma.platform.bukkit.player

import de.jalumu.magma.platform.base.player.BasePlayerProvider
import de.jalumu.magma.platform.bukkit.bootstrap.MagmaBukkitBootstrap
import de.jalumu.magma.player.MagmaPlayer
import org.bukkit.entity.Player
import java.util.*

class BukkitPlayerProvider(private val magma: MagmaBukkitBootstrap) : BasePlayerProvider() {
    override fun getOnlinePlayer(name: String): MagmaPlayer {
        val player = magma.server.getPlayer(name)
        return BukkitPlayer(magma, player!!)
    }

    override fun getOnlinePlayer(uuid: UUID): MagmaPlayer {
        val player = magma.server.getPlayer(uuid)
        return BukkitPlayer(magma, player!!)
    }

    override fun getOnlinePlayers(): Array<MagmaPlayer> {
        return magma.server.onlinePlayers.stream().map { player: Player? -> BukkitPlayer(magma, player!!) }.toArray() as Array<MagmaPlayer>
    }
}