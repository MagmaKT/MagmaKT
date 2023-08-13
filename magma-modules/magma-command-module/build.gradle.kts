plugins {
    java
}

group = rootProject.group
version = rootProject.version

val paperVersion = ext.get("paperVersion") as String
val waterfallVersion = ext.get("waterfallVersion") as String

dependencies {

    // Magma-Dependencies
    compileOnly(project(":magma-platform:magma-platform-bukkit"))
    compileOnly(project(":magma-platform:magma-platform-bungee"))

    // Minecraft-Dependencies
    compileOnly("io.papermc.paper:paper-api:$paperVersion")
    compileOnly("io.github.waterfallmc:waterfall-api:$waterfallVersion")

    //Annotations
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
}