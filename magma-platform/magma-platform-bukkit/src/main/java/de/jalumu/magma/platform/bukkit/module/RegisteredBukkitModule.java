package de.jalumu.magma.platform.bukkit.module;

import de.jalumu.magma.platform.base.module.MagmaModule;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class RegisteredBukkitModule implements ConfigurationSerializable {

    private MagmaModule module;
    private String name;
    private String clazz;

    public RegisteredBukkitModule(MagmaModule module) {
        this.module = module;
    }

    public RegisteredBukkitModule(Map<String, Object> map) {
        name = map.get("name").toString();
        clazz = map.get("class").toString();
    }

    public String getName() {
        return name;
    }

    @Override
    public Map<String, Object> serialize() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("class", clazz);
        return map;
    }
}
