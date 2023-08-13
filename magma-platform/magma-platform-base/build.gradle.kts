plugins {
    kotlin("jvm")
    id("maven-publish")
    id("org.jetbrains.kotlin.kapt")
}

group = rootProject.group
version = rootProject.version

repositories {
    mavenCentral()
}

dependencies {

    // Magma-Dependencies
    api(project(":magma-api"))
    implementation("net.kyori:adventure-platform-api:4.1.1")

    // Minecraft-Dependencies
    compileOnly("com.mojang:authlib:3.11.50")

    // Module-Dependencies
    api("io.github.classgraph:classgraph:4.8.150")

    // Database-Dependencies
    api("com.zaxxer:HikariCP:5.0.1")

    // Utils
    api("org.json:json:20220924")
    api("com.google.code.gson:gson:2.10")
    implementation("com.google.guava:guava:31.1-jre")

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

kapt {
    keepJavacAnnotationProcessors = true
}