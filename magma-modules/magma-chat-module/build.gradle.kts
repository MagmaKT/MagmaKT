plugins {
    java
}

group = rootProject.group
version = rootProject.version

dependencies {
    compileOnly(project(":magma-platform:magma-platform-bukkit"))
}