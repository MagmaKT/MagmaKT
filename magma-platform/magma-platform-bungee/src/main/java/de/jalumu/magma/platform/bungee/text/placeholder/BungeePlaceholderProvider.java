package de.jalumu.magma.platform.bungee.text.placeholder;

import de.jalumu.magma.text.placeholder.PlaceholderProvider;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

import java.util.UUID;

public class BungeePlaceholderProvider extends PlaceholderProvider {
    @Override
    protected TagResolver getGlobal() {
        return TagResolver.builder().build();
    }

    @Override
    protected TagResolver getPlayer(UUID uuid) {
        return TagResolver.builder().build();
    }
}
