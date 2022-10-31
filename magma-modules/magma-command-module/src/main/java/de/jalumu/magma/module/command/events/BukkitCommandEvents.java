package de.jalumu.magma.module.command.events;

import com.destroystokyo.paper.event.server.AsyncTabCompleteEvent;
import de.jalumu.magma.module.command.MagmaCommandModule;
import de.jalumu.magma.platform.base.text.notification.Notification;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.TabCompleteEvent;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BukkitCommandEvents implements org.bukkit.event.Listener {

    private YamlConfiguration configuration;

    public BukkitCommandEvents(MagmaCommandModule module) {
        File config = new File(module.getDataFolder(), "command.yml");
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

        configuration.addDefault("command.whitelist.enabled", false);
        configuration.addDefault("command.whitelist.whitelist", List.of("magma", "help"));
        configuration.addDefault("command.msg.blocked", "You are not allowed to use this command!");

        configuration.options().copyDefaults(true);

        try {
            configuration.save(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onTab(TabCompleteEvent event) {

    }

    @EventHandler
    public void onPreProcess(PlayerCommandPreprocessEvent event) {
        if (configuration.getBoolean("command.whitelist.enabled") && !event.getPlayer().hasPermission("magma.command.whitelist.bypass")) {
            List<String> whitelist = configuration.getStringList("command.whitelist.whitelist");
            if (!whitelist.contains(event.getMessage().split(" ")[0].replace("/", ""))) {
                Notification.error(configuration.getString("command.msg.blocked")).send(event.getPlayer());
                event.setCancelled(true);
            }
        }
    }

}
