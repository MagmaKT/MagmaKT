package de.keeeks.magma.core.spigot;

import de.keeeks.magma.core.api.ModuleLoader;
import de.keeeks.magma.core.spigot.commands.ModulesCommand;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import revxrsal.commands.bukkit.BukkitCommandHandler;

public final class MagmaKTSpigotPlugin extends JavaPlugin {
    @Getter
    @Accessors(fluent = true)
    private static MagmaKTSpigotPlugin plugin;

    private final ModuleLoader moduleLoader = ModuleLoader.create(
            Bukkit.getLogger(),
            getClassLoader()
    );

    @Getter
    @Accessors(fluent = true)
    private BukkitCommandHandler commandHandler;
    private BukkitAudiences adventure;

    public @NonNull BukkitAudiences adventure() {
        if (this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
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
        adventure = BukkitAudiences.create(this);
        commandHandler = BukkitCommandHandler.create(this);

        commandHandler.register(ModulesCommand.create(moduleLoader));
        moduleLoader.enable();
    }

    @Override
    public void onDisable() {
        moduleLoader.disable();
    }
}