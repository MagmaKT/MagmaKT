package de.keeeks.magma.core.proxy;

import de.keeeks.magma.core.api.ModuleLoader;
import de.keeeks.magma.core.proxy.commands.ModulesCommand;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import net.kyori.adventure.platform.bungeecord.BungeeAudiences;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import revxrsal.commands.CommandHandler;
import revxrsal.commands.bungee.BungeeCommandHandler;

public final class MagmaKTProxyPlugin extends Plugin {
    @Getter
    @Accessors(fluent = true)
    private static MagmaKTProxyPlugin plugin;
    private final ModuleLoader moduleLoader = ModuleLoader.create(
            ProxyServer.getInstance().getLogger(),
            ProxyServer.getInstance().getPluginManager().getClass().getClassLoader()
    );
    @Getter
    @Accessors(fluent = true)
    private final CommandHandler commandHandler = BungeeCommandHandler.create(this);

    private BungeeAudiences adventure;

    public @NonNull BungeeAudiences adventure() {
        if (this.adventure == null) {
            throw new IllegalStateException("Cannot retrieve audience provider while plugin is not enabled");
        }
        return this.adventure;
    }


    @Override
    public void onLoad() {
        plugin = this;
        moduleLoader.load();
        moduleLoader.initialize();
    }

    @Override
    public void onEnable() {
        adventure = BungeeAudiences.create(this);
        moduleLoader.enable();

        commandHandler.register(ModulesCommand.create(moduleLoader));
    }

    @Override
    public void onDisable() {
        moduleLoader.disable();
    }
}