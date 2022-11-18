package de.keeeks.magma.chat.listener;

import de.keeeks.magma.chat.configuration.MessagesConfiguration;
import de.keeeks.magma.core.placeholders.Placeholders;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class PlayerQuitListener implements Listener {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    private final MessagesConfiguration messagesConfiguration;

    @EventHandler
    public void handleQuit(PlayerQuitEvent event) {
        var player = event.getPlayer();
        var component = miniMessage.deserialize(
                messagesConfiguration.leaveFormat(),
                Placeholders.getPlayer(player.getName(), player.getUniqueId())
        );
        event.quitMessage(component);
    }

    public static PlayerQuitListener create(MessagesConfiguration messagesConfiguration) {
        return new PlayerQuitListener(messagesConfiguration);
    }
}