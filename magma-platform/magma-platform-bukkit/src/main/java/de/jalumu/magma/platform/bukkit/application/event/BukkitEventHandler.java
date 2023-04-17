package de.jalumu.magma.platform.bukkit.application.event;

import de.jalumu.magma.platform.bukkit.application.BukkitApplication;
import org.bukkit.event.Listener;

public class BukkitEventHandler implements Listener {

    private BukkitApplication application;
    private String name;

    public BukkitEventHandler(BukkitApplication application, String name){
        this.application = application;
        this.name = name;
    }

}
