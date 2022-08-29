package de.jalumu.magma.annotation.bungee.platform.application

@Target(AnnotationTarget.CLASS)
annotation class BungeecordPlugin(

    val name: String,
    val version: String,
    val description: String = "A Magma-Application",
    val prefix: String = "",
    val author: String = "Unknown",
    val authors: Array<String> = [],
    val dependsPlugin: Array<String> = ["Magma"],
    val softDependsPlugin: Array<String> = [],
    val loadBeforePlugin: Array<String> = [],

    )
