package de.keeeks.magma.core.api;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

@Getter
@Accessors(fluent = true)
public abstract class Module {
    private static final Map<String, Module> modules = new ConcurrentHashMap<>();

    private final File defaultConfig;
    private final ModuleDescription description;

    protected Logger logger;

    public Module() {
        this.description = this.getClass().getDeclaredAnnotation(ModuleDescription.class);
        this.defaultConfig = new File("magma-kt/config", description.name() + ".yml");
        modules.put(description.name(), this);
    }

    public void initialize() {
    }

    public void enable() {
    }

    public void disable() {
    }

    public void reload() {
    }

    public void updateLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        var module = (Module) obj;
        return Objects.equals(description.name(), module.description.name());
    }

    public static Module get(String name) {
        return modules.get(name);
    }

    public static <T extends Module> T get(Class<T> clazz) {
        return modules.values().stream().filter(module ->
                module.getClass() == clazz
        ).map(module -> (T) module).findFirst().orElse(null);
    }

}