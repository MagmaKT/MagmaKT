package de.keeeks.magma.chat;

import de.keeeks.magma.chat.configuration.MessagesConfiguration;
import de.keeeks.magma.chat.listener.PlayerJoinListener;
import de.keeeks.magma.chat.listener.PlayerQuitListener;
import de.keeeks.magma.config.toml.TomlConfiguration;
import de.keeeks.magma.configuration.api.Configuration;
import de.keeeks.magma.core.api.ModuleDescription;
import de.keeeks.magma.core.spigot.SpigotModule;

@ModuleDescription(name = "chat", description = "Handles the chat on a spigot server", depends = "json-config")
public class ChatModule extends SpigotModule {
    private final Configuration configuration = TomlConfiguration.create(
            "chat",
            "messages"
    );
    private final MessagesConfiguration messagesConfiguration = configuration.readObject(
            MessagesConfiguration.class,
            MessagesConfiguration.createDefault()
    );

    @Override
    public void enable() {
        if (messagesConfiguration.joinQuitMessagesEnabled()) {
            registerListeners(
                    PlayerJoinListener.create(messagesConfiguration),
                    PlayerQuitListener.create(messagesConfiguration)
            );
        }
    }
}