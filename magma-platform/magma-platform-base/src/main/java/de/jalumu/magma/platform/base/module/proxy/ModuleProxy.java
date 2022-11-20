package de.jalumu.magma.platform.base.module.proxy;

import de.jalumu.magma.platform.base.module.MagmaModule;
import de.jalumu.magma.platform.base.platform.ServerImplementation;

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
