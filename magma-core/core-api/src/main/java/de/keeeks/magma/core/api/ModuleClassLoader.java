package de.keeeks.magma.core.api;

import java.net.URL;
import java.net.URLClassLoader;

public class ModuleClassLoader extends URLClassLoader {

    private ModuleClassLoader(ClassLoader parent) {
        super(new URL[0], parent);
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }

    public static ModuleClassLoader create(ClassLoader classLoader) {
        return new ModuleClassLoader(classLoader);
    }
}