plugins {
    kotlin("jvm")
    java
    id("com.github.johnrengelman.shadow")
    id("org.jetbrains.kotlin.kapt")
    id("maven-publish")
}

group = rootProject.group
version = rootProject.version

val waterfallVersion = ext.get("waterfallVersion") as String
val adventureVersion = ext.get("adventureVersion") as String
val adventurePlatformVersion = ext.get("adventurePlatformVersion") as String

dependencies {

    // Magma-Dependencies
    api(project(":magma-platform:magma-platform-base"))
    implementation("net.kyori:adventure-platform-bungeecord:$adventurePlatformVersion")

    // Minecraft-Dependencies
    compileOnly("io.github.waterfallmc:waterfall-api:$waterfallVersion")

    //Command
    api("com.github.Revxrsal.Lamp:bungee:3.1.1")

    //Config
    implementation("de.exlll:configlib-waterfall:4.2.0")

    // Annotations
    compileOnly(project(":magma-annotations:magma-annotations-bungee-platform"))
    kapt(project(":magma-annotations:magma-annotations-bungee-platform"))

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
        exclude(dependency("io.github.waterfallmc:waterfall-api:1.19-R0.1-SNAPSHOT"))
    }

    //relocate "org.bstats", "de.jalumu.magma.platform.bukkit.bstats"

}