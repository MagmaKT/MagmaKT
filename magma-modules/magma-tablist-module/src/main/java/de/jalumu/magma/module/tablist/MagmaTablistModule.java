package de.jalumu.magma.module.tablist;

import de.jalumu.magma.module.tablist.events.ConnectionEvents;
import de.jalumu.magma.module.tablist.handler.TablistHandler;
import de.jalumu.magma.module.tablist.util.NameChanger;
import de.jalumu.magma.platform.base.module.MagmaModule;
import de.jalumu.magma.platform.base.platform.MagmaPlatform;
import de.jalumu.magma.platform.base.platform.MagmaPlatformType;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class MagmaTablistModule implements MagmaModule {

    private MagmaPlatform platform;

    private Permission perms = null;

    private NameChanger nameChanger;

    @Override
    public String getName() {
        return "Magma-Tablist";
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {
        setupPermissions();
        //TablistHandler.init(this);

        nameChanger = new NameChanger(getBukkit());


        Bukkit.getPluginManager().registerEvents(new ConnectionEvents(this), (Plugin) platform.getMagmaPluginInstance());

        Bukkit.getScheduler().runTaskTimer(getBukkit(),bukkitTask -> {
            TablistHandler.updateDecoration();
        },5,20);

    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onUnload() {

    }

    @Override
    public MagmaPlatform getPlatform() {
        return platform;
    }

    @Override
    public boolean isCompatible(MagmaPlatform platform) {
        if (platform.getPlatformType() == MagmaPlatformType.GAMESERVER) {
            this.platform = platform;
            return true;
        }
        return false;
    }

    public JavaPlugin getBukkit(){
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
