package de.jalumu.magma.platform.bungee.player

import de.jalumu.magma.platform.bungee.bootstrap.MagmaBungeeBootstrap
import de.jalumu.magma.player.MagmaPlayer
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.platform.bungeecord.BungeeAudiences
import net.md_5.bungee.api.connection.ProxiedPlayer
import java.util.*

class BungeePlayer(private val magma: MagmaBungeeBootstrap, private val player: ProxiedPlayer) : MagmaPlayer {
    override fun getName(): String {
        return player.name
    }

    override fun getUniqueId(): UUID {
        return player.uniqueId
    }

    override fun isLegacy(): Boolean {
        return false
    }

    override fun isOnline(): Boolean {
        return player.isConnected
    }

    override fun getDisplayName(): String {
        return player.displayName
    }

    override fun getAudience(): Audience {
        val audiences = magma.audience as BungeeAudiences
        return audiences.player(player)
    }
}
