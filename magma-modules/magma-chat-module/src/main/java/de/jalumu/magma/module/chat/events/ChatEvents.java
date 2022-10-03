package de.jalumu.magma.module.chat.events;

import de.jalumu.magma.platform.base.module.MagmaModule;
import de.jalumu.magma.platform.base.text.placeholder.Placeholders;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvents implements Listener {

    private MagmaModule module;

    public ChatEvents(MagmaModule module) {
        this.module = module;
    }

    @EventHandler
    public void onChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        Component chatFormat = MiniMessage.miniMessage().deserialize("<rank_displayname> <dark_gray>| <player_first_prefix_color><player_name> <dark_gray>-<reset> <message>", Placeholders.player(player.getUniqueId()), Placeholder.component("message", event.message()));
        for (Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage(chatFormat);
        }
        event.setCancelled(true);
    }

}
