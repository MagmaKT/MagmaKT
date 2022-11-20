package de.jalumu.magma.module.command.proxy;

import de.jalumu.magma.module.command.MagmaCommandModule;
import de.jalumu.magma.module.command.events.BukkitCommandEvents;
import de.jalumu.magma.platform.base.module.MagmaModule;
import de.jalumu.magma.platform.base.module.proxy.Proxy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PaperProxy implements Proxy {

    @Override
    public void run(MagmaModule module) {
        Bukkit.getPluginManager().registerEvents(new BukkitCommandEvents((MagmaCommandModule) module), (JavaPlugin) module.getPlatform().getMagmaPluginInstance());
    }
}
