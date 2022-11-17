package de.jalumu.magma.platform.bungee.module;

import de.jalumu.magma.platform.base.module.MagmaModule;

import java.util.HashMap;
import java.util.Map;

public class RegisteredBungeeModule {

    private String name;
    private String clazz;

    public RegisteredBungeeModule(MagmaModule module) {
        name = module.getName();
        clazz = module.getClass().getName();
    }

    public RegisteredBungeeModule(Map<String, Object> map) {
        name = map.get("name").toString();
        clazz = map.get("class").toString();
    }


    public String getName() {
        return name;
    }

    public Map<String, Object> serialize() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("class", clazz);
        return map;
    }
}
