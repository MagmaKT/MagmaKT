package de.keeeks.magma.chat.listener;

import de.keeeks.magma.chat.configuration.MessagesConfiguration;
import de.keeeks.magma.core.placeholders.Placeholders;
import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.AsyncChatEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerChatListener implements Listener {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    private final MessagesConfiguration messagesConfiguration;

    @EventHandler
    public void handleChat(AsyncChatEvent event) {
        var player = event.getPlayer();

        event.renderer(new ChatRenderer() {
            @Override
            public @NotNull Component render(
                    @NotNull Player source,
                    @NotNull Component sourceDisplayName,
                    @NotNull Component message,
                    @NotNull Audience viewer) {
                /*var component = miniMessage.deserialize(
                        messagesConfiguration.chatFormat(),
                        Placeholders.getPlayer(source.getName(), source.getUniqueId()),
                        TagResolver.builder()
                                .resolver()
                                .build()
                );*/



                return null;
            }
        });
    }

}