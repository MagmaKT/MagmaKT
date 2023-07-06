package de.jalumu.magma.platform.base.platform.util

import de.jalumu.magma.platform.MagmaPlatform

object SplashScreen {
    fun splashScreen(platform: MagmaPlatform) {
        val logger = platform.magmaLogger
        logger.info("")
        logger.info("-------------------------------------------------------------------------------------")
        logger.info("")
        logger.info("    _/      _/                                                _/    _/  _/_/_/_/_/   ")
        logger.info("   _/_/  _/_/    _/_/_/    _/_/_/  _/_/_/  _/_/      _/_/_/  _/  _/        _/        ")
        logger.info("  _/  _/  _/  _/    _/  _/    _/  _/    _/    _/  _/    _/  _/_/          _/         ")
        logger.info(" _/      _/  _/    _/  _/    _/  _/    _/    _/  _/    _/  _/  _/        _/          ")
        logger.info("_/      _/    _/_/_/    _/_/_/  _/    _/    _/    _/_/_/  _/    _/      _/           ")
        logger.info("                           _/                                                        ")
        logger.info("                      _/_/                                                           ")
        logger.info("")
        logger.info("-------------------------------------------------------------------------------------")
        logger.info("")
        logger.info(platform.magmaImplementationName + " (" + platform.magmaImplementationVersion + ")")
        logger.info("Server implementation: " + platform.platformName + " (" + platform.platformVersion + ")")
        logger.info("Server ID: " + platform.serverID)
        logger.info("Type: " + platform.platformType)
        logger.info("")
        logger.info("-------------------------------------------------------------------------------------")
        logger.info("")
    }
}
