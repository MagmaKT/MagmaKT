package de.jalumu.magma.platform.bukkit.extension

import net.kyori.adventure.platform.AudienceProvider
import net.kyori.adventure.platform.bukkit.BukkitAudiences

fun AudienceProvider.bukkit() : BukkitAudiences {
    return this as BukkitAudiences}