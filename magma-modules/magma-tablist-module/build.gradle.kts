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

    // External PLugins
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
    compileOnly("net.luckperms:api:5.4")
    compileOnly("com.comphenix.protocol:ProtocolLib:4.8.0")
}