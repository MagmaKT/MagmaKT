package de.jalumu.magma.platform.base.module;

import de.jalumu.magma.platform.base.platform.MagmaPlatform;

public interface MagmaModule {

    String getName();

    void onLoad();
    void onEnable();
    void onDisable();
    void onUnload();

    MagmaPlatform getPlatform();

}
