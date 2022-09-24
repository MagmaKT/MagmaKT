package de.jalumu.magma.module.console.events;

import de.jalumu.magma.platform.base.module.MagmaModule;
import de.jalumu.magma.platform.base.text.Notification;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class ChatEvents implements Listener {

    private MagmaModule module;

    public ChatEvents(MagmaModule module){
        this.module = module;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        //event.setMessage("ASDF");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Notification.debug("Test").send(event.getPlayer());
        Notification.info("Test").send(event.getPlayer());
        Notification.success("Test").send(event.getPlayer());
        Notification.warning("Test").send(event.getPlayer());
        Notification.error("Test").send(event.getPlayer());
        event.getPlayer().sendMessage("Ok?");
    }

}
