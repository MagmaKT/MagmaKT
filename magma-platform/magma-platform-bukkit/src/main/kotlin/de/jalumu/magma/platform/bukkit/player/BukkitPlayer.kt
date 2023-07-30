package de.jalumu.magma.platform.bukkit.player

import de.jalumu.magma.platform.bukkit.bootstrap.MagmaBukkitBootstrap
import de.jalumu.magma.player.MagmaPlayer
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.platform.bukkit.BukkitAudiences
import org.bukkit.entity.Player
import java.util.*

/**
 * The Bukkit implementation for MagmaPlayer
 */
class BukkitPlayer(private val magma: MagmaBukkitBootstrap, private val player: Player) : MagmaPlayer {
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
        return player.isOnline
    }

    override fun getDisplayName(): String {
        return player.displayName
    }

    override fun getAudience(): Audience {
        val audiences = magma.audience as BukkitAudiences
        return audiences.player(player)
    }
}