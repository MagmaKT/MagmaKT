package de.jalumu.magma.platform.base.module

import lombok.SneakyThrows
import java.io.File
import java.net.URLClassLoader

class ModuleClassLoader(parent: ClassLoader?) : URLClassLoader(arrayOf(), parent) {
    @SneakyThrows
    fun addJar(jar: File) {
        super.addURL(jar.toURI().toURL())
    }
}
