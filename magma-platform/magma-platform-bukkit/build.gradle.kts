plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow")
    id("org.jetbrains.kotlin.kapt")
    id("maven-publish")
}

group = rootProject.group
version = rootProject.version

dependencies {

    // Magma-Dependencies
    api(project(":magma-platform:magma-platform-base"))
    api("net.kyori:adventure-platform-bukkit:4.1.1")

    // Minecraft-Dependencies
    api("io.papermc.paper:paper-api:1.19.3-R0.1-SNAPSHOT")

    //Command
    api("com.github.Revxrsal.Lamp:bukkit:3.1.1")

    // Config
    api("de.exlll:configlib-paper:4.2.0")

    // Gui
    api("dev.triumphteam:triumph-gui:3.1.2")

    // Analytics
    implementation("org.bstats:bstats-bukkit:3.0.0")

    // External Plugins
    compileOnly("net.luckperms:api:5.4")
    compileOnly("me.clip:placeholderapi:2.11.2")
    compileOnly("com.comphenix.protocol:ProtocolLib:4.8.0")

    // Annotations
    compileOnly(project(":magma-annotations:magma-annotations-bukkit-platform"))
    kapt(project(":magma-annotations:magma-annotations-bukkit-platform"))
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")

}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/JaLuMu/MagmaKT")
            credentials {
                username = System.getenv("USERNAME")
                password = System.getenv("TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}

tasks.shadowJar {
    dependencies {
        exclude(dependency("io.papermc.paper:paper-api:1.19.3-R0.1-SNAPSHOT"))
    }

    relocate("org.bstats", "de.jalumu.magma.platform.bukkit.bstats")

}