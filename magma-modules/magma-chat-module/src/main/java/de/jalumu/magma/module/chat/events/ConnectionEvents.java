package de.jalumu.magma.module.chat.events;

import de.jalumu.magma.module.chat.MagmaChatModule;
import de.jalumu.magma.text.placeholder.Placeholders;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;

public class ConnectionEvents implements Listener {


    private MagmaChatModule module;

    private YamlConfiguration configuration;

    public ConnectionEvents(MagmaChatModule magmaChatModule) {
        module = magmaChatModule;

        File config = new File(module.getDataFolder(), "join.yml");
        configuration = new YamlConfiguration();

        if (!config.exists()) {
            try {
                config.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            configuration.load(config);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }

        configuration.addDefault("join.format", "<dark_gray>[<green>+<dark_gray>] <rank_displayname> <dark_gray>| <player_first_prefix_color><player_name>");
        configuration.addDefault("leave.format", "<dark_gray>[<red>-<dark_gray>] <rank_displayname> <dark_gray>| <player_first_prefix_color><player_name>");


        configuration.options().copyDefaults(true);

        try {
            configuration.save(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.joinMessage(MiniMessage.miniMessage().deserialize(configuration.getString("join.format"), Placeholders.player(event.getPlayer().getUniqueId())));
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        event.quitMessage(MiniMessage.miniMessage().deserialize(configuration.getString("leave.format"), Placeholders.player(event.getPlayer().getUniqueId())));
    }

}
