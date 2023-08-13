plugins {
    id("java-library")
    id("maven-publish")
    kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

val adventureVersion = ext.get("adventureVersion") as String

repositories {
    mavenCentral()
}

dependencies {

    // Adventure
    api("net.kyori:adventure-api:$adventureVersion")
    api("net.kyori:adventure-text-minimessage:$adventureVersion")
    api("net.kyori:adventure-text-serializer-legacy:$adventureVersion")

    // Config
    api("de.exlll:configlib-core:4.2.0")
    api("de.exlll:configlib-yaml:4.2.0")

    // Command
    api("com.github.Revxrsal.Lamp:common:3.1.1")

    // Annotations
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