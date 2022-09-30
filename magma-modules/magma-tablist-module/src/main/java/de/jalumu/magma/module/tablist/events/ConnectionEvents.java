package de.jalumu.magma.module.tablist.events;

import de.jalumu.magma.module.tablist.MagmaTablistModule;
import de.jalumu.magma.module.tablist.handler.TablistHandler;
import de.jalumu.magma.platform.base.text.placeholder.Placeholders;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
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

        File config = new File(module.getBukkit().getDataFolder(), "tablist.yml");
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

        configuration.options().copyDefaults(true);

        try {
            configuration.save(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TablistHandler.init(magmaTablistModule, configuration);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        TablistHandler.updateTablist();
    }

    public void onQuit(PlayerQuitEvent event) {
        TablistHandler.updateTablist();
    }

}
