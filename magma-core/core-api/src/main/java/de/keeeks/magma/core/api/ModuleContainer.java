package de.keeeks.magma.core.api;

import java.util.concurrent.atomic.AtomicBoolean;

public record ModuleContainer(String name,
                              String[] dependencies,
                              String[] softDependencies,
                              AtomicBoolean enabled,
                              ModuleDescription description,
                              Class<? extends Module> mainClass) {
    public void enable() {
        enabled.set(true);
    }

    public static ModuleContainer create(ModuleDescription description, Class<? extends Module> mainClass) {
        return new ModuleContainer(
                description.name(),
                description.depends(),
                description.softDepends(),
                new AtomicBoolean(false),
                description,
                mainClass
        );
    }
}