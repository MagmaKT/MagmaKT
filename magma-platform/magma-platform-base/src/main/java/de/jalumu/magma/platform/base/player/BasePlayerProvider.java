package de.jalumu.magma.platform.base.player;

import com.mojang.util.UUIDTypeAdapter;
import de.jalumu.magma.player.FetchedPlayer;
import de.jalumu.magma.player.PlayerProvider;
import lombok.SneakyThrows;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class BasePlayerProvider extends PlayerProvider {

    @Override
    @SneakyThrows
    protected FetchedPlayer fetchPlayer(String name) {

        URL endpoint = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
        HttpURLConnection connection = (HttpURLConnection) endpoint.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = reader.lines().collect(Collectors.joining());;
        reader.close();
        connection.disconnect();

        JSONObject json = new JSONObject(response);

        String uuid = json.getString("id");
        UUID uniqueId = UUIDTypeAdapter.fromString(uuid);


        return fetchPlayer(uniqueId);
    }

    @Override
    @SneakyThrows
    protected FetchedPlayer fetchPlayer(UUID uuid) {

        URL endpoint = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid.toString());
        HttpURLConnection connection = (HttpURLConnection) endpoint.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = reader.lines().collect(Collectors.joining());;
        reader.close();
        connection.disconnect();

        JSONObject json = new JSONObject(response);

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
