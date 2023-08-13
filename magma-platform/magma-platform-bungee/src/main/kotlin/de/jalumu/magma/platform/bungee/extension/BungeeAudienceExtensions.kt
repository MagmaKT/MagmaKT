package de.jalumu.magma.platform.bungee.extension

import net.kyori.adventure.platform.AudienceProvider
import net.kyori.adventure.platform.bungeecord.BungeeAudiences

fun AudienceProvider.bungee(): BungeeAudiences {
    return this as BungeeAudiences
}
