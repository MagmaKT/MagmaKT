plugins {
    java
}

group = rootProject.group
version = rootProject.version

val paperVersion = ext.get("paperVersion") as String

dependencies {

    // Magma-Dependencies
    compileOnly(project(":magma-platform:magma-platform-bukkit"))

    // SimpleCloud
    compileOnly("eu.thesimplecloud.simplecloud:simplecloud-plugin:2.4.1")

    // Minecraft-Dependencies
    compileOnly("io.papermc.paper:paper-api:$paperVersion")

    // Annotations
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
}