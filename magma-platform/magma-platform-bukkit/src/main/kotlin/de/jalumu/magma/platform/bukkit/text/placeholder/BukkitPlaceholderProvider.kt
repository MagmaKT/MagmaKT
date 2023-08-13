package de.jalumu.magma.platform.bukkit.text.placeholder

import de.jalumu.magma.platform.bukkit.bootstrap.MagmaBukkitBootstrap
import de.jalumu.magma.text.placeholder.PlaceholderProvider
import me.clip.placeholderapi.PlaceholderAPI
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import net.luckperms.api.LuckPermsProvider
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

/**
 * A provider for bukkit's default placeholders
 */
class BukkitPlaceholderProvider(private val magma: MagmaBukkitBootstrap) : PlaceholderProvider() {
    override fun getGlobal(): TagResolver {
        return TagResolver.builder()
            .resolver(Placeholder.parsed("local_player_online", Bukkit.getOnlinePlayers().size.toString()))
            .build()
    }

    override fun getPlayer(uuid: UUID): TagResolver {
        val player = Bukkit.getPlayer(uuid)
        return TagResolver.builder()
            .resolver(global)
            .resolver(
                Placeholder.parsed(
                    "bungee_player_online",
                    PlaceholderAPI.setPlaceholders(player, "%bungee_total%")
                )
            )
            .resolver(Placeholder.parsed("player_name", player!!.name))
            .resolver(Placeholder.parsed("player_uuid", uuid.toString()))
            .resolver(Placeholder.parsed("rank_displayname", getPlayerGroupName(player)!!))
            .resolver(Placeholder.parsed("player_prefix", getPlayerPrefix(player)!!))
            .resolver(Placeholder.parsed("player_first_prefix_color", getPlayerPrefixColor(player)))
            .build()
    }

    private fun getPlayerPrefix(player: Player?): String? {
        val api = LuckPermsProvider.get()
        return api.getPlayerAdapter(Player::class.java).getUser(player!!).cachedData.metaData.prefix
    }

    private fun getPlayerGroupName(player: Player?): String? {
        val api = LuckPermsProvider.get()
        val group = api.getPlayerAdapter(Player::class.java).getUser(
            player!!
        ).cachedData.metaData.primaryGroup
        return api.groupManager.getGroup(group!!)!!.displayName
    }

    private fun getPlayerPrefixColor(player: Player?): String {
        val prefix = getPlayerPrefix(player)
        val split = prefix!!.split(">".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        return split[0] + ">"
    }
}