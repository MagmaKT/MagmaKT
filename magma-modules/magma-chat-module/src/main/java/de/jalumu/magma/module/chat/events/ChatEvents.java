package de.jalumu.magma.module.chat.events;

import de.jalumu.magma.module.chat.MagmaChatModule;
import de.jalumu.magma.text.placeholder.Placeholders;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;

public class ChatEvents implements Listener {

    private MagmaChatModule module;

    private YamlConfiguration configuration;

    public ChatEvents(MagmaChatModule magmaChatModule) {
        module = magmaChatModule;

        File config = new File(module.getDataFolder(), "chat.yml");
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

        configuration.addDefault("chat.format", "<rank_displayname> <dark_gray>| <player_first_prefix_color><player_name> <dark_gray>-<reset> <message>");


        configuration.options().copyDefaults(true);

        try {
            configuration.save(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        Component chatFormat = MiniMessage.miniMessage().deserialize(configuration.getString("chat.format"), Placeholders.player(player.getUniqueId()), Placeholder.component("message", event.message()));
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(chatFormat);
        }
        event.setCancelled(true);
    }

}
