package de.jalumu.magma.module;

import de.jalumu.magma.platform.MagmaPlatform;

import java.util.logging.Logger;

/**
 * An interface for Magma Modules
 */
public interface MagmaModule {

    void onLoad();
    void onEnable();
    void onDisable();
    void onUnload();

    MagmaPlatform getPlatform();

    ModuleMeta getMeta();

    Logger getLogger();
    
}
