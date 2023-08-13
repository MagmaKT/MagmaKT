plugins {
    id("java-library")
    id("maven-publish")
    kotlin("jvm")
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
}

dependencies {

    // Adventure
    api("net.kyori:adventure-api:4.11.0")
    api("net.kyori:adventure-text-minimessage:4.11.0")
    api("net.kyori:adventure-text-serializer-legacy:4.11.0")

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