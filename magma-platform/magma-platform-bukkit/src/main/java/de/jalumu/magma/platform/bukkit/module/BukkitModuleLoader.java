package de.jalumu.magma.platform.bukkit.module;

import de.jalumu.magma.module.chat.MagmaChatModule;
import de.jalumu.magma.platform.base.module.MagmaModule;
import de.jalumu.magma.platform.base.module.ModuleLoader;
import de.jalumu.magma.platform.bukkit.bootstrap.MagmaBukkitBootstrap;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

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
                config.mkdir();
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
            if (!configuration.getList("modules.registered").contains(bukkitModule)) {
                List<RegisteredBukkitModule> registered = (List<RegisteredBukkitModule>) configuration.getList("modules.registered");
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
}
