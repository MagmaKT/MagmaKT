package de.keeeks.magma.chat.listener;

import de.keeeks.magma.chat.configuration.MessagesConfiguration;
import de.keeeks.magma.core.placeholders.Placeholders;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerJoinListener implements Listener {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    private final MessagesConfiguration messagesConfiguration;

    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event) {
        var player = event.getPlayer();
        var component = miniMessage.deserialize(
                messagesConfiguration.joinFormat(),
                Placeholders.getPlayer(player.getName(), player.getUniqueId())
        );
        event.joinMessage(component);
    }

    public static PlayerJoinListener create(MessagesConfiguration messagesConfiguration) {
        return new PlayerJoinListener(messagesConfiguration);
    }
}