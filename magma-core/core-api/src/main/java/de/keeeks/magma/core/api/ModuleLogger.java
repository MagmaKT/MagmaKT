package de.keeeks.magma.core.api;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class ModuleLogger extends Logger {

    protected ModuleLogger(String module, Logger parent) {
        super("MagmaKT/" + module, null);
        setParent(parent);
        setLevel(Level.ALL);
    }

    @Override
    public void log(LogRecord record) {
        getParent().log(record);
    }

    public static ModuleLogger create(Module module, Logger parent) {
        return new ModuleLogger(module.description().name(), parent);
    }
}