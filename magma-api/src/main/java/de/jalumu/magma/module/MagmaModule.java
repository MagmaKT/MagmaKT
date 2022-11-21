package de.jalumu.magma.module;

import java.util.logging.Logger;

public interface MagmaModule {

    void onLoad();
    void onEnable();
    void onDisable();
    void onUnload();

    ModuleMeta getMeta();

    Logger getLogger();
    
}
