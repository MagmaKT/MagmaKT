package de.jalumu.magma.platform.bukkit.module;

import de.jalumu.magma.platform.base.module.MagmaModule;
import de.jalumu.magma.platform.base.module.ModuleLoader;
import de.jalumu.magma.platform.bukkit.bootstrap.MagmaBukkitBootstrap;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class BukkitModuleLoader implements ModuleLoader {

    private MagmaBukkitBootstrap bukkitBootstrap;

    private HashMap<String, MagmaModule> modules = new HashMap<>();

    private File config;
    private YamlConfiguration configuration;

    public BukkitModuleLoader(MagmaBukkitBootstrap bukkitBootstrap) {
        this.bukkitBootstrap = bukkitBootstrap;

        config = new File(bukkitBootstrap.getDataFolder(), "modules/modules.yml");
        configuration = new YamlConfiguration();

        if (!config.exists()) {
            try {
                config.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            configuration.load(config);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }

        ArrayList<RegisteredBukkitModule> modules = new ArrayList<>();
        configuration.addDefault("modules.registered", modules);

        configuration.options().copyDefaults(true);

        try {
            configuration.save(config);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void registerModule(MagmaModule module) {
        if (module.isCompatible()) {
            RegisteredBukkitModule bukkitModule = new RegisteredBukkitModule(module);
            if (Objects.requireNonNull(configuration.getList("modules.registered")).stream().noneMatch(o -> ((RegisteredBukkitModule) o).getName().equals(bukkitModule.getName()))) {
                List<RegisteredBukkitModule> registered = (List<RegisteredBukkitModule>) configuration.getList("modules.registered");
                assert registered != null;
                registered.add(bukkitModule);
                configuration.set("modules.registered", registered);
                try {
                    configuration.save(config);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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

    @Override
    public MagmaModule getModule(String name) {
        return modules.get(name);
    }

    public void autoLoad() {
        for (RegisteredBukkitModule module : (List<RegisteredBukkitModule>) Objects.requireNonNull(configuration.getList("modules.registered"))) {
            if (configuration.contains("modules.enabled." + module.getName())) {
                if (configuration.getBoolean("modules.enabled." + module.getName())) {
                    enableModule(module.getName());
                }
            } else {
                configuration.set("modules.enabled." + module.getName(), true);
                try {
                    configuration.save(config);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
