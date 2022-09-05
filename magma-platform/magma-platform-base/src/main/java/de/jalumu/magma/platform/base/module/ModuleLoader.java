package de.jalumu.magma.platform.base.module;

import java.util.Collection;
import java.util.Set;

public interface ModuleLoader {

    void registerModule(MagmaModule module);
    Collection<MagmaModule> getRegisteredModules();

    void unregisterModule(MagmaModule module);
    void unregisterModule(String name);

    void enableModule(MagmaModule module);
    void enableModule(String name);

    void disableModule(MagmaModule module);
    void disableModule(String name);


}
