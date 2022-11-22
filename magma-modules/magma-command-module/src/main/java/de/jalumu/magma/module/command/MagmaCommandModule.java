package de.jalumu.magma.module.command;

import de.jalumu.magma.module.BaseModule;
import de.jalumu.magma.module.ModuleMeta;
import de.jalumu.magma.module.command.proxy.BungeeProxy;
import de.jalumu.magma.module.command.proxy.PaperProxy;
import de.jalumu.magma.module.proxy.ModuleProxy;
import de.jalumu.magma.platform.MagmaPlatformType;
import de.jalumu.magma.platform.ServerImplementation;

@ModuleMeta(
        name = "MagmaCommandModule",
        version = "1.0.0",
        author = "Jalumu",
        description = "A command module for Magma",
        supportedPlatforms = {MagmaPlatformType.GAMESERVER, MagmaPlatformType.PROXY},
        supportedServerImplementations = {ServerImplementation.PAPER, ServerImplementation.BUNGEECORD}
)
public class MagmaCommandModule extends BaseModule {

    @Override
    public void onEnable() {
        ModuleProxy proxy = new ModuleProxy(this);
        proxy.registerProxy(ServerImplementation.PAPER, new PaperProxy());
        proxy.registerProxy(ServerImplementation.BUNGEECORD, new BungeeProxy());
    }

}
