package de.jalumu.magma.module.command.proxy;

import de.jalumu.magma.module.MagmaModule;
import de.jalumu.magma.module.command.events.BungeeCommandEvents;
import de.jalumu.magma.module.proxy.Proxy;

public class BungeeProxy implements Proxy {
    @Override
    public void run(MagmaModule module) {
        new BungeeCommandEvents((de.jalumu.magma.module.command.MagmaCommandModule) module);
    }
}
