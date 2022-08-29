package de.jalumu.magma.annotation.bukkit.platform.application

@Target(AnnotationTarget.CLASS)
annotation class BukkitPlugin(

    val name: String,
    val version: String,
    val description: String = "A Magma-Application",
    val prefix: String = "",
    val apiVersion: BukkitApiVersion = BukkitApiVersion.V1_19,
    val loadOn: BukkitApplicationStartProperty = BukkitApplicationStartProperty.POSTWORLD,
    val author: String = "Unknown",
    val authors: Array<String> = [],
    val dependsPlugin: Array<String> = ["Magma-Bukkit"],
    val softDependsPlugin: Array<String> = [],
    val loadBeforePlugin: Array<String> = [],

    )
