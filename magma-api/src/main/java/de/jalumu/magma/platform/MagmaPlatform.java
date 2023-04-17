package de.jalumu.magma.platform;

import de.jalumu.magma.module.ModuleLoader;
import revxrsal.commands.CommandHandler;

import java.util.logging.Logger;

public interface MagmaPlatform {

    String getName();

    String getVersion();

    MagmaPlatformType getPlatformType();

    ServerImplementation getServerImplementation();

    String getPlatformName();

    String getPlatformVersion();

    String getServerID();

    void setServerID(String serverID);

    Logger getLogger();

    CommandHandler getCommandHandler();

    Object getMagmaPluginInstance();

    static MagmaPlatform getPlatform() {
        return MagmaPlatformProvider.getPlatform();
    }


}
