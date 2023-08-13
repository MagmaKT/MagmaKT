plugins {
    kotlin("jvm")
    java
    id("maven-publish")
}

group = rootProject.group
version = rootProject.version

val paperVersion = ext.get("paperVersion") as String

dependencies {
    compileOnly("io.papermc.paper:paper-api:$paperVersion")
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