package de.jalumu.magma.platform;

import de.jalumu.magma.module.ModuleLoader;

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


}
