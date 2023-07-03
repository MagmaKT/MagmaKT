package de.jalumu.magma.platform.bungee.text.placeholder

import de.jalumu.magma.text.placeholder.PlaceholderProvider
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import java.util.*

class BungeePlaceholderProvider : PlaceholderProvider() {
    override fun getGlobal(): TagResolver {
        return TagResolver.builder().build()
    }

    override fun getPlayer(uuid: UUID): TagResolver {
        return TagResolver.builder().build()
    }
}
