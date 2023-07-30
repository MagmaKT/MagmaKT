package de.jalumu.magma.platform.bukkit.extension

import net.kyori.adventure.platform.AudienceProvider
import net.kyori.adventure.platform.bukkit.BukkitAudiences

/**
 * An Extension for the AudienceProvider
 */
fun AudienceProvider.bukkit() : BukkitAudiences {
    return this as BukkitAudiences}