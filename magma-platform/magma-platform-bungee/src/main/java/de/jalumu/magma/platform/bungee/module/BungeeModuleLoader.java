package de.jalumu.magma.platform.bungee.module;

import de.jalumu.magma.platform.MagmaPlatform;
import de.jalumu.magma.platform.base.module.ModuleLoader;
import de.jalumu.magma.platform.bungee.bootstrap.MagmaBungeeBootstrap;

import java.io.File;

public class BungeeModuleLoader extends ModuleLoader {

    public BungeeModuleLoader(MagmaPlatform platform, File moduleDirectory) {
        super(platform, moduleDirectory);
    }

    @Override
    protected boolean isPlatformPluginAvailable(String plugin) {
        return ((MagmaBungeeBootstrap) getPlatform().getMagmaPluginInstance()).getProxy().getPluginManager().getPlugin(plugin) != null;
    }
}
