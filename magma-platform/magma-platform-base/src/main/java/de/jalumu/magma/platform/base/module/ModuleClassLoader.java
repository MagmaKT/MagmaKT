package de.jalumu.magma.platform.base.module;

import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class ModuleClassLoader extends URLClassLoader {

    public ModuleClassLoader(ClassLoader parent) {
        super(new URL[]{}, parent);
    }

    @SneakyThrows
    public void addJar(File jar) {
        super.addURL(jar.toURI().toURL());
    }

}
