package de.jalumu.magma.platform.base.platform.util;

import de.jalumu.magma.platform.MagmaPlatform;

import java.util.logging.Logger;

public class SplashScreen {

    public static void splashScreen(MagmaPlatform platform) {
        Logger logger = platform.getLogger();
        logger.info("");
        logger.info("-------------------------------------------------------------------------------------");
        logger.info("");
        logger.info("    _/      _/                                                _/    _/  _/_/_/_/_/   ");
        logger.info("   _/_/  _/_/    _/_/_/    _/_/_/  _/_/_/  _/_/      _/_/_/  _/  _/        _/        ");
        logger.info("  _/  _/  _/  _/    _/  _/    _/  _/    _/    _/  _/    _/  _/_/          _/         ");
        logger.info(" _/      _/  _/    _/  _/    _/  _/    _/    _/  _/    _/  _/  _/        _/          ");
        logger.info("_/      _/    _/_/_/    _/_/_/  _/    _/    _/    _/_/_/  _/    _/      _/           ");
        logger.info("                           _/                                                        ");
        logger.info("                      _/_/                                                           ");
        logger.info("");
        logger.info("-------------------------------------------------------------------------------------");
        logger.info("");
        logger.info(platform.getName() + " (" + platform.getVersion() + ")");
        logger.info("Server implementation: " + platform.getPlatformName() + " (" + platform.getPlatformVersion() + ")");
        logger.info("Server ID: " + platform.getServerID());
        logger.info("Type: " + platform.getPlatformType());
        logger.info("");
        logger.info("-------------------------------------------------------------------------------------");
        logger.info("");
    }

}
