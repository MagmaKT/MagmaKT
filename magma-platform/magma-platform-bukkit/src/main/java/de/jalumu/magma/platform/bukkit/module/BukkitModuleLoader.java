package de.jalumu.magma.platform.bukkit.module;

import de.jalumu.magma.platform.base.module.MagmaModule;
import de.jalumu.magma.platform.base.module.ModuleLoader;
import de.jalumu.magma.platform.bukkit.bootstrap.MagmaBukkitBootstrap;

import java.util.Collection;
import java.util.HashMap;

public class BukkitModuleLoader implements ModuleLoader {

    private MagmaBukkitBootstrap paperBootstrap;

    private HashMap<String, MagmaModule> modules = new HashMap<>();

    public BukkitModuleLoader(MagmaBukkitBootstrap paperBootstrap) {
        this.paperBootstrap = paperBootstrap;
    }

    @Override
    public void registerModule(MagmaModule module) {
        if (module.isCompatible()) {
            modules.put(module.getName(), module);
            module.onLoad();
        }
    }

    @Override
    public Collection<MagmaModule> getRegisteredModules() {
        return modules.values();
    }

    @Override
    public void unregisterModule(MagmaModule module) {
        modules.remove(module);
        module.onUnload();
    }

    @Override
    public void unregisterModule(String name) {
        modules.get(name).onUnload();
        modules.remove(name);
    }

    @Override
    public void enableModule(MagmaModule module) {
        if (modules.containsValue(module)) {
            module.onEnable();
        }
    }

    @Override
    public void enableModule(String name) {
        if (modules.containsKey(name)) {
            modules.get(name).onEnable();
        }
    }

    @Override
    public void disableModule(MagmaModule module) {
        if (modules.containsValue(module)) {
            module.onDisable();
        }
    }

    @Override
    public void disableModule(String name) {
        if (modules.containsKey(name)) {
            modules.get(name).onDisable();
        }
    }
}
