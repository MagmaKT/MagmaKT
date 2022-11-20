package de.jalumu.magma.text.placeholder;

import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

import java.util.UUID;

public interface Placeholders {

    static TagResolver global(){
        return PlaceholderProvider.getProvider().getGlobal();
    }

    static TagResolver player(UUID uuid){
        return PlaceholderProvider.getProvider().getPlayer(uuid);
    }

}
