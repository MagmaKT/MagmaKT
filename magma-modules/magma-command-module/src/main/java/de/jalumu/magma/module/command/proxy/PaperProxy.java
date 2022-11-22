package de.jalumu.magma.module.command.proxy;

import de.jalumu.magma.module.MagmaModule;
import de.jalumu.magma.module.command.MagmaCommandModule;
import de.jalumu.magma.module.command.events.BukkitCommandEvents;
import de.jalumu.magma.module.proxy.Proxy;

public class PaperProxy implements Proxy {
    @Override
    public void run(MagmaModule module) {
        new BukkitCommandEvents((MagmaCommandModule) module);
    }
}
