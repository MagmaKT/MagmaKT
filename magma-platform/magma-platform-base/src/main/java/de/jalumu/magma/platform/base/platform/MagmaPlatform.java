package de.jalumu.magma.platform.base.platform;

import de.jalumu.magma.platform.base.module.ModuleLoader;

import java.util.logging.Logger;

public interface MagmaPlatform {

    String getName();
    String getVersion();
    MagmaPlatformType getPlatformType();
    ServerImplementation getServerImplementation();
    String getPlatformName();
    String getPlatformVersion();
    Logger getLogger();

    Object getMagmaPluginInstance();

    ModuleLoader getModuleLoader();

}
