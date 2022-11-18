package de.jalumu.magma.annotation.bukkit.platform.command

import de.jalumu.magma.annotation.bukkit.platform.permission.PermissionDefault

@Target(AnnotationTarget.CLASS)
annotation class Command(

    val name: String,
    val description: String = "A Magma-Command",
    val aliases: Array<String> = [],
    val permission: String,
    val permissionDefault: PermissionDefault = PermissionDefault.GRANTED

)
