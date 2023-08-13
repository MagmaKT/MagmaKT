plugins {
    java
}

group = rootProject.group
version = rootProject.version

val paperVersion = ext.get("paperVersion") as String

dependencies {

    // Magma-Dependencies
    compileOnly(project(":magma-platform:magma-platform-bukkit"))

    // Minecraft-Dependencies
    compileOnly("io.papermc.paper:paper-api:$paperVersion")
}