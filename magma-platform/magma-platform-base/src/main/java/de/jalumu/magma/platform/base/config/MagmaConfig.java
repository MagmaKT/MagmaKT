package de.jalumu.magma.platform.base.config;

import java.io.File;

public interface MagmaConfig {

    void read();

    void read(File file);

    void setObject(String key, Object o);

    void setString(String key, String s);

    void setInt(String key, int i);

    void save();

    void save(File file);

}
