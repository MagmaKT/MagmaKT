package de.jalumu.magma.module.command;

import de.jalumu.magma.module.command.proxy.PaperProxy;
import de.jalumu.magma.platform.base.module.ModuleBase;
import de.jalumu.magma.platform.base.module.proxy.ModuleProxy;
import de.jalumu.magma.platform.base.platform.MagmaPlatform;
import de.jalumu.magma.platform.base.platform.ServerImplementation;

import java.io.File;

public class MagmaCommandModule extends ModuleBase {

    public MagmaCommandModule(MagmaPlatform platform, File dataFolder) {
        super(platform, dataFolder);
    }

    @Override
    public String getName() {
        return "Magma-Command";
    }

    @Override
    public void onEnable() {
        ModuleProxy proxy = new ModuleProxy(this);
        proxy.registerProxy(ServerImplementation.PAPER, new PaperProxy());

    }


    @Override
    public boolean isCompatible() {
        return true;
    }
}
