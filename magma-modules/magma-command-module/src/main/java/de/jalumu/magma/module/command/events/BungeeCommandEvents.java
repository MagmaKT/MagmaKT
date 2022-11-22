package de.jalumu.magma.module.command.events;

import de.exlll.configlib.YamlConfigurations;
import de.jalumu.magma.module.command.MagmaCommandModule;
import de.jalumu.magma.module.command.config.MagmaCommandConfig;
import io.github.waterfallmc.waterfall.event.ProxyDefineCommandsEvent;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.io.File;

public class BungeeCommandEvents implements Listener {

    MagmaCommandConfig config;

    public BungeeCommandEvents(MagmaCommandModule module) {
        ProxyServer.getInstance().getPluginManager().registerListener((Plugin) module.getPlatform().getMagmaPluginInstance(), this);
        File configFile = new File(module.getDataFolder(), "config.yml");

        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                config = new MagmaCommandConfig();
                YamlConfigurations.save(configFile.toPath(), MagmaCommandConfig.class, config);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                config = YamlConfigurations.load(configFile.toPath(), MagmaCommandConfig.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @EventHandler
    public void onCommandDefine(ProxyDefineCommandsEvent event) {
        event.getCommands().keySet().removeIf(command -> !config.getAllowedCommands().contains(command));
    }

}
