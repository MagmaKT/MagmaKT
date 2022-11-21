package de.jalumu.magma.platform.base.module;

import java.util.logging.Logger;

public class ModuleLogger extends Logger {

    protected ModuleLogger(String moduleName, Logger parent) {
        super("MagmaKT-" + moduleName, null);
        setParent(parent);
        setLevel(parent.getLevel());
    }
}
