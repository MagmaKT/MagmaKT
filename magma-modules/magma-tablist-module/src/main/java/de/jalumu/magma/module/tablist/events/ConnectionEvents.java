package de.jalumu.magma.module.tablist.events;

import de.jalumu.magma.module.tablist.MagmaTablistModule;
import de.jalumu.magma.module.tablist.handler.TablistHandler;
import de.jalumu.magma.player.FetchedPlayer;
import de.jalumu.magma.player.Players;
import de.jalumu.magma.text.placeholder.Placeholders;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;

public class ConnectionEvents implements Listener {

    private MagmaTablistModule module;

    private YamlConfiguration configuration;

    public ConnectionEvents(MagmaTablistModule magmaTablistModule) {
        module = magmaTablistModule;

        File config = new File(module.getDataFolder(), "tablist.yml");
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

        configuration.addDefault("tablist.decoration.header", "<gold>Servername.net<newline><newline><gold>%server_name%<newline><red>Player<gray>:%server_online%<newline>");
        configuration.addDefault("tablist.decoration.footer", "<newline><red>Teamspeak<gray>: <gold>ts.servername.net<newline><dark_purple>Discord<gray>: <gold>discord.servername.net<newline>");

        configuration.addDefault("tablist.player.prefix", "<player_prefix> <dark_gray>| <player_first_prefix_color>");
        configuration.addDefault("tablist.player.displayname", "<player_first_prefix_color><player_name>");
        configuration.addDefault("tablist.player.listName", "<player_prefix> <dark_gray>| <player_first_prefix_color><player_name>");


        configuration.options().copyDefaults(true);

        try {
            configuration.save(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TablistHandler.init(magmaTablistModule, configuration);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        Component tablistName = MiniMessage.miniMessage().deserialize(configuration.getString("tablist.player.listName"), Placeholders.player(event.getPlayer().getUniqueId()));
        Component displayname = MiniMessage.miniMessage().deserialize(configuration.getString("tablist.player.displayname"), Placeholders.player(event.getPlayer().getUniqueId()));
        event.getPlayer().playerListName(tablistName);
        event.getPlayer().displayName(displayname);
        event.getPlayer().customName(displayname);
        event.getPlayer().setCustomNameVisible(true);

        //if (event.getPlayer().getName().length() < 14) {
        //module.getNameChanger().changeName(event.getPlayer(), LegacyComponentSerializer.builder().character(LegacyComponentSerializer.SECTION_CHAR).build().serialize(displayname));
        //}

        TablistHandler.updateTablist();
    }

    public void onQuit(PlayerQuitEvent event) {
        TablistHandler.updateTablist();
    }

}
