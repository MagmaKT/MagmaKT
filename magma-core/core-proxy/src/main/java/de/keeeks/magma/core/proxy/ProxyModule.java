package de.keeeks.magma.core.proxy;

import de.keeeks.magma.core.api.Module;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.PluginManager;

public class ProxyModule extends Module {
    private final PluginManager pluginManager = ProxyServer.getInstance().getPluginManager();
    private final MagmaKTProxyPlugin plugin = MagmaKTProxyPlugin.plugin();

    public void registerCommand(Object command) {
        registerCommands(command);
    }

    public void registerCommands(Object... commands) {
        plugin.commandHandler().register(commands);
    }

    public void registerListener(Listener listener) {
        pluginManager.registerListener(MagmaKTProxyPlugin.plugin(), listener);
    }

    public void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            registerListener(listener);
        }
    }
}