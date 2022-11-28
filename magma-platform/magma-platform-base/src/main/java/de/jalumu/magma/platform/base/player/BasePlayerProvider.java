package de.jalumu.magma.platform.base.player;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.util.UUIDTypeAdapter;
import de.jalumu.magma.platform.base.util.ApiUtil;
import de.jalumu.magma.player.FetchedPlayer;
import de.jalumu.magma.player.PlayerProvider;
import lombok.SneakyThrows;
import org.json.JSONObject;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public abstract class BasePlayerProvider extends PlayerProvider {

    private CacheLoader<String, String> cacheLoader = new CacheLoader<String, String>() {
        @Override
        public String load(String key) throws Exception {
            return ApiUtil.getResultFromUrl(key);
        }
    };

    private LoadingCache<String, String> cache = CacheBuilder.newBuilder().expireAfterAccess(5, TimeUnit.MINUTES).build(cacheLoader);

    @Override
    @SneakyThrows
    protected FetchedPlayer fetchPlayer(String name) {
        JSONObject json = new JSONObject(ApiUtil.getResultFromUrl("https://api.mojang.com/users/profiles/minecraft/" + name));
        String uuid = json.getString("id");
        UUID uniqueId = UUIDTypeAdapter.fromString(uuid);
        return fetchPlayer(uniqueId);
    }

    @Override
    @SneakyThrows
    protected FetchedPlayer fetchPlayer(UUID uuid) {
        JSONObject json = new JSONObject(ApiUtil.getResultFromUrl("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid.toString()));
        String name = json.getString("name");
        return new FetchedPlayer() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public UUID getUniqueId() {
                return uuid;
            }

            @Override
            public boolean isLegacy() {
                return json.has("legacy") && json.getBoolean("legacy");
            }

            @Override
            public boolean isOnline() {
                return getOnlinePlayer(uuid) != null;
            }
        };
    }

    @Override
    @SneakyThrows
    protected FetchedPlayer getCachedPlayer(String name) {
        JSONObject json = new JSONObject(cache.get("https://api.mojang.com/users/profiles/minecraft/" + name));
        String uuid = json.getString("id");
        UUID uniqueId = UUIDTypeAdapter.fromString(uuid);
        return fetchPlayer(uniqueId);
    }

    @Override
    @SneakyThrows
    protected FetchedPlayer getCachedPlayer(UUID uuid) {
        JSONObject json = new JSONObject(cache.get("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid.toString()));
        String name = json.getString("name");
        return new FetchedPlayer() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public UUID getUniqueId() {
                return uuid;
            }

            @Override
            public boolean isLegacy() {
                return json.has("legacy") && json.getBoolean("legacy");
            }

            @Override
            public boolean isOnline() {
                return getOnlinePlayer(uuid) != null;
            }
        };
    }

}
