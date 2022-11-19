package de.jalumu.magma.text.placeholder;

import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

import java.util.UUID;

public abstract class PlaceholderProvider {

    private static PlaceholderProvider placeholderProvider = null;

    public static PlaceholderProvider getProvider(){
        return placeholderProvider;
    }

    public static void setProvider(PlaceholderProvider placeholderProvider){
        PlaceholderProvider.placeholderProvider = placeholderProvider;
    }

    protected abstract TagResolver getGlobal();
    protected abstract TagResolver getPlayer(UUID uuid);

}
