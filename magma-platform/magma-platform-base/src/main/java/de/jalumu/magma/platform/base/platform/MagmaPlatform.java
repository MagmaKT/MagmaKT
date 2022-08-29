package de.jalumu.magma.platform.base.platform;

import java.util.logging.Logger;

public interface MagmaPlatform {

    String getName();
    String getVersion();
    MagmaPlatformType getPlatformType();
    String getPlatformName();
    String getPlatformVersion();
    Logger getLogger();

}
