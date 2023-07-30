package de.jalumu.magma.util.sandbox;

import de.jalumu.magma.platform.MagmaPlatform;
import de.jalumu.magma.player.MagmaPlayer;

import java.util.HashMap;
import java.util.Set;

/**
 * A Sandbox is a helper class to test code ingame
 */
public interface Sandbox {

    HashMap<String, Sandbox> SANDBOX_HASH_MAP = new HashMap<>();

    static void register(String name, Sandbox sandbox) {
        SANDBOX_HASH_MAP.put(name, sandbox);
    }

    static void remove(String name) {
        SANDBOX_HASH_MAP.remove(name);
    }

    static void run(String name, MagmaPlayer player, MagmaPlatform platform) {
        SANDBOX_HASH_MAP.get(name).runSandbox(player, platform);
    }

    static Set<String> getKeys() {
        return SANDBOX_HASH_MAP.keySet();
    }

    static void clearAll(String name) {
        SANDBOX_HASH_MAP.clear();
    }

    void runSandbox(MagmaPlayer player, MagmaPlatform platform);

}
