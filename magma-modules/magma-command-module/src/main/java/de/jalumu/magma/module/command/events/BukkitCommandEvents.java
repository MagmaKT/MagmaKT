package de.jalumu.magma.module.command.events;

import de.exlll.configlib.YamlConfigurations;
import de.jalumu.magma.module.command.MagmaCommandModule;
import de.jalumu.magma.module.command.config.MagmaCommandBukkitConfig;
import de.jalumu.magma.text.notification.Notification;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.command.UnknownCommandEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BukkitCommandEvents implements org.bukkit.event.Listener {

    private MagmaCommandBukkitConfig config;

    public BukkitCommandEvents(MagmaCommandModule module) {
        Bukkit.getPluginManager().registerEvents(this, (JavaPlugin) module.getPlatform().getMagmaPluginInstance());

        File configFile = new File(module.getDataFolder(), "config.yml");

        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                config = new MagmaCommandBukkitConfig();
                YamlConfigurations.save(configFile.toPath(), MagmaCommandBukkitConfig.class, config);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                config = YamlConfigurations.load(configFile.toPath(), MagmaCommandBukkitConfig.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @EventHandler
    public void onPreProcess(PlayerCommandPreprocessEvent event) {
        if (config.isWhitelistEnabled() && !event.getPlayer().hasPermission("magma.command.whitelist.bypass")) {
            List<String> whitelist = config.getAllowedCommands();
            if (!whitelist.contains(event.getMessage().split(" ")[0].replace("/", ""))) {
                Notification.error(config.getCommandNotFoundMessage()).send(event.getPlayer());
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onCommandSend(PlayerCommandSendEvent event) {
        if (!event.getPlayer().hasPermission("magma.command.whitelist.bypass")) {
            List<String> whitelist = config.getAllowedCommands();
            event.getCommands().removeIf(command -> !whitelist.contains(command));
        }
    }

    @EventHandler
    public void onUnknownCommand(UnknownCommandEvent event) {
        event.message(Notification.error(config.getCommandNotFoundMessage()).getNotificationText());
    }

}
