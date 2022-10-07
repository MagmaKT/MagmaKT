package de.jalumu.magma.platform.bukkit.module;

import de.jalumu.magma.platform.base.module.MagmaModule;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class RegisteredBukkitModule implements ConfigurationSerializable {

    private MagmaModule module;

    public RegisteredBukkitModule(MagmaModule module) {
        this.module = module;
    }


    @Override
    public Map<String, Object> serialize() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", module.getName());
        map.put("class", module.getClass().getName());
        return map;
    }
}
