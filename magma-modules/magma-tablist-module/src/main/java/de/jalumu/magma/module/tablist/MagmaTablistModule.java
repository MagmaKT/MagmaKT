package de.jalumu.magma.module.tablist;

import de.jalumu.magma.module.tablist.events.ConnectionEvents;
import de.jalumu.magma.module.tablist.handler.TablistHandler;
import de.jalumu.magma.module.tablist.util.NameChanger;
import de.jalumu.magma.platform.base.module.ModuleBase;
import de.jalumu.magma.platform.base.platform.MagmaPlatform;
import de.jalumu.magma.platform.base.platform.MagmaPlatformType;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class MagmaTablistModule extends ModuleBase {

    private Permission perms = null;

    private NameChanger nameChanger;

    public MagmaTablistModule(MagmaPlatform platform, File dataFolder) {
        super(platform, dataFolder);
    }

    @Override
    public String getName() {
        return "Magma-Tablist";
    }

    @Override
    public void onEnable() {
        setupPermissions();
        //TablistHandler.init(this);

        nameChanger = new NameChanger(getBukkit());

        Bukkit.getPluginManager().registerEvents(new ConnectionEvents(this), (Plugin) getPlatform().getMagmaPluginInstance());

        Bukkit.getScheduler().runTaskTimer(getBukkit(), bukkitTask -> {
            TablistHandler.updateDecoration();
        }, 5, 20);

    }

    @Override
    public boolean isCompatible() {
        return getPlatform().getPlatformType() == MagmaPlatformType.GAMESERVER;
    }

    public JavaPlugin getBukkit() {
        return (JavaPlugin) getPlatform();
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getBukkit().getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public Permission getPerms() {
        return perms;
    }

    public NameChanger getNameChanger() {
        return nameChanger;
    }
}
