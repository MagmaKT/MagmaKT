package de.keeeks.magma.core.spigot;

import de.keeeks.magma.core.api.Module;
import org.bukkit.event.Listener;

public class SpigotModule extends Module {

    public void registerCommand(Object command) {
        registerCommands(command);
    }

    public void registerCommands(Object... commands) {
        MagmaKTSpigotPlugin.plugin().commandHandler().register(commands);
    }

    public void registerListener(Listener listener) {

    }

    public void registerListeners(Listener... listeners) {

    }

}