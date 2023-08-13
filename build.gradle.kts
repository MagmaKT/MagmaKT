plugins {
    kotlin("jvm") version "1.6.10"
    java
    id("maven-publish")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.jetbrains.dokka") version "1.8.20"
}

group = "de.jalumu.magma"
version = "1.0.24"

allprojects {

    apply(plugin = "org.jetbrains.dokka")

    this.group = group
    this.version = group


    repositories {
    
        maven {
            url = uri("https://maven.pkg.github.com/JaLuMu/MagmaKT")
            credentials {
                username = System.getenv("USERNAME")
                password = System.getenv("TOKEN")
            }
        }

        maven {
            url = uri("https://maven.pkg.github.com/Exlll/ConfigLib")
            credentials {
                username = System.getenv("USERNAME")
                password = System.getenv("TOKEN")
            }
        }

        maven {
            name = "jitpack"
            url = uri("https://jitpack.io")
        }
        maven {
            name = "papi"
            url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/")
        }
        maven {
            name = "papermc"
            url = uri("https://repo.papermc.io/repository/maven-public/")
        }
        maven {
            name = "dmulloy2"
            url = uri("https://repo.dmulloy2.net/repository/public/")
        }
        maven {
            name = "SimpleCloud"
            url = uri("https://repo.thesimplecloud.eu/artifactory/gradle-release-local/")
        }
    }

}

dependencies {
    api(project(":magma-annotations:magma-annotations-bukkit-platform"))
    api(project(":magma-annotations:magma-annotations-bungee-platform"))
    api(project(":magma-platform:magma-platform-bukkit"))
    api(project(":magma-platform:magma-platform-bungee"))
}

tasks.shadowJar {

    dependencies {
        exclude(dependency("io.github.waterfallmc:waterfall-api:1.19-R0.1-SNAPSHOT"))
        exclude(dependency("io.papermc.paper:paper-api:1.19.3-R0.1-SNAPSHOT"))
    }

    relocate("org.bstats", "de.jalumu.magma.platform.bukkit.bstats")
}
