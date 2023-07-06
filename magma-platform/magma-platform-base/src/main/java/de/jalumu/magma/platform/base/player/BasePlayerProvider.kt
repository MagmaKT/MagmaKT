package de.jalumu.magma.platform.base.player

import com.google.common.cache.CacheBuilder
import com.google.common.cache.CacheLoader
import com.mojang.util.UUIDTypeAdapter
import de.jalumu.magma.platform.base.util.ApiUtil
import de.jalumu.magma.player.FetchedPlayer
import de.jalumu.magma.player.PlayerProvider
import lombok.SneakyThrows
import org.json.JSONObject
import java.util.*
import java.util.concurrent.TimeUnit

abstract class BasePlayerProvider : PlayerProvider() {
    private val cacheLoader: CacheLoader<String, String> = object : CacheLoader<String, String>() {
        @Throws(Exception::class)
        override fun load(key: String?): String {
            return ApiUtil.getResultFromUrl(key)
        }
    }
    private val cache = CacheBuilder.newBuilder().expireAfterAccess(5, TimeUnit.MINUTES).build(cacheLoader)
    @SneakyThrows
    override fun fetchPlayer(name: String): FetchedPlayer {
        val json = JSONObject(ApiUtil.getResultFromUrl("https://api.mojang.com/users/profiles/minecraft/$name"))
        val uuid = json.getString("id")
        val uniqueId = UUIDTypeAdapter.fromString(uuid)
        return fetchPlayer(uniqueId)
    }

    @SneakyThrows
    override fun fetchPlayer(uuid: UUID): FetchedPlayer {
        val json =
            JSONObject(ApiUtil.getResultFromUrl("https://sessionserver.mojang.com/session/minecraft/profile/$uuid"))
        val name = json.getString("name")
        return object : FetchedPlayer {
            override fun getName(): String {
                return name
            }

            override fun getUniqueId(): UUID {
                return uuid
            }

            override fun isLegacy(): Boolean {
                return json.has("legacy") && json.getBoolean("legacy")
            }

            override fun isOnline(): Boolean {
                return getOnlinePlayer(uuid) != null
            }
        }
    }

    @SneakyThrows
    override fun getCachedPlayer(name: String): FetchedPlayer {
        val json = JSONObject(cache["https://api.mojang.com/users/profiles/minecraft/$name"])
        val uuid = json.getString("id")
        val uniqueId = UUIDTypeAdapter.fromString(uuid)
        return fetchPlayer(uniqueId)
    }

    @SneakyThrows
    override fun getCachedPlayer(uuid: UUID): FetchedPlayer {
        val json = JSONObject(cache["https://sessionserver.mojang.com/session/minecraft/profile/$uuid"])
        val name = json.getString("name")
        return object : FetchedPlayer {
            override fun getName(): String {
                return name
            }

            override fun getUniqueId(): UUID {
                return uuid
            }

            override fun isLegacy(): Boolean {
                return json.has("legacy") && json.getBoolean("legacy")
            }

            override fun isOnline(): Boolean {
                return getOnlinePlayer(uuid) != null
            }
        }
    }
}
