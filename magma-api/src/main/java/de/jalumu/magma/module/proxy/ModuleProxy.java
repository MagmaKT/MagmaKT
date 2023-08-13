package de.jalumu.magma.module.proxy;

import de.jalumu.magma.module.MagmaModule;
import de.jalumu.magma.platform.ServerImplementation;

/**
 * A wrapper for @{@link Proxy} to execute platform dependent code
 */
public class ModuleProxy {

    private MagmaModule module;

    public ModuleProxy(MagmaModule module) {
        this.module = module;
    }

    public void registerProxy(ServerImplementation implementation, Proxy proxy) {
        if (module.getPlatform().getServerImplementation() == implementation) {
            proxy.run(module);
        }
    }


}
