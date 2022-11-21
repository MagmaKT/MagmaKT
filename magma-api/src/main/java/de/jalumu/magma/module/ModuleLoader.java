package de.jalumu.magma.module;

import java.util.Collection;

public interface ModuleLoader {

    void registerModule(MagmaModule module);
    Collection<MagmaModule> getRegisteredModules();

    void unregisterModule(MagmaModule module);
    void unregisterModule(String name);

    void enableModule(MagmaModule module);
    void enableModule(String name);

    void disableModule(MagmaModule module);
    void disableModule(String name);

    MagmaModule getModule(String name);


}
