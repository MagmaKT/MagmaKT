package de.jalumu.magma.module.chat.events;

import de.jalumu.magma.platform.base.module.MagmaModule;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvents implements Listener {

    private MagmaModule module;

    public ChatEvents(MagmaModule module){
        this.module = module;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        //event.setMessage("ASDF");
    }

}
