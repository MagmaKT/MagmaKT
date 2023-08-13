plugins {
    java
}

group = rootProject.group
version = rootProject.version

dependencies {

    // Magma-Dependencies
    compileOnly(project(":magma-platform:magma-platform-bukkit"))

    // SimpleCloud
    compileOnly("eu.thesimplecloud.simplecloud:simplecloud-plugin:2.4.1")

    // Annotations
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
}