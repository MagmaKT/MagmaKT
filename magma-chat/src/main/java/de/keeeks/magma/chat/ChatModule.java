package de.keeeks.magma.chat;

import de.keeeks.magma.chat.commands.TestCommand;
import de.keeeks.magma.core.api.ModuleDescription;
import de.keeeks.magma.core.spigot.SpigotModule;

@ModuleDescription(name = "chat", description = "Handles the chat on a spigot server", depends = "json-config")
public class ChatModule extends SpigotModule {

    @Override
    public void enable() {
        registerCommands(new TestCommand());
    }
}