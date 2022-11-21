package de.jalumu.magma.module;

public interface MagmaModule {

    void onLoad();
    void onEnable();
    void onDisable();
    void onUnload();

    ModuleMeta getMeta();
    
}
