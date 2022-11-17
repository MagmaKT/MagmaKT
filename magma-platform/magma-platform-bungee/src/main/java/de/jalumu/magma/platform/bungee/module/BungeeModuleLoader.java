package de.jalumu.magma.platform.bungee.module;

import de.exlll.configlib.Comment;
import de.exlll.configlib.YamlConfigurations;
import de.jalumu.magma.module.command.MagmaCommandModule;
import de.jalumu.magma.platform.base.module.MagmaModule;
import de.jalumu.magma.platform.bungee.bootstrap.MagmaBungeeBootstrap;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class BungeeModuleLoader {

    private HashMap<String, MagmaModule> modules = new HashMap<>();

    private File config;

    private MagmaBungeeBootstrap bungeeBootstrap;

    public BungeeModuleLoader(MagmaBungeeBootstrap bungeeBootstrap) {
        this.bungeeBootstrap = bungeeBootstrap;

        config = new File(bungeeBootstrap.getDataFolder(), "modules/modules.yml");

        ModuleConfiguration moduleConfiguration = new ModuleConfiguration();

        moduleConfiguration.registered.add(new RegisteredBungeeModule(new MagmaCommandModule(bungeeBootstrap, config)));

        if (!config.exists()) {
            try {
                config.createNewFile();
                YamlConfigurations.save(Paths.get(config.toURI()), ModuleConfiguration.class, moduleConfiguration);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @de.exlll.configlib.Configuration
    public static class ModuleConfiguration {
        @Comment("Dont touch this!")
        private ArrayList<RegisteredBungeeModule> registered = new ArrayList<>();

    }


}


