package de.keeeks.magma.config.toml;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import de.keeeks.magma.configuration.api.Configuration;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class TomlConfiguration extends Configuration {

    private final Toml toml;

    public TomlConfiguration(String path, String fileName) {
        super(path, fileName, ConfigurationType.TOML);

        toml = new Toml().read(file);
    }

    public String getString(String path) {
        return getString(path, "A default value");
    }

    public String getString(String path, String defaultValue) {
        return toml.getString(path, defaultValue);
    }

    public Long getLong(String path) {
        return getLong(path, 1L);
    }

    public Long getLong(String path, Long defaultValue) {
        return toml.getLong(path, defaultValue);
    }

    public <T> List<T> getList(String path) {
        return getList(path, List.of());
    }

    public <T> List<T> getList(String path, List<T> defaultValue) {
        return toml.getList(path, defaultValue);
    }

    public Boolean getBoolean(String path) {
        return getBoolean(path, false);
    }

    public Boolean getBoolean(String path, Boolean defaultValue) {
        return toml.getBoolean(path, defaultValue);
    }

    public Date getDate(String path) {
        return getDate(path, Date.from(Instant.ofEpochMilli(0)));
    }

    public Date getDate(String path, Date defaultValue) {
        return toml.getDate(path, defaultValue);
    }

    public Double getDouble(String path) {
        return getDouble(path, 1.0);
    }

    public Double getDouble(String path, Double defaultValue) {
        return toml.getDouble(path, defaultValue);
    }

    public boolean contains(String path) {
        return toml.contains(path);
    }

    public boolean containsPrimitive(String path) {
        return toml.containsPrimitive(path);
    }

    public boolean isEmpty() {
        return toml.isEmpty();
    }

    @Override
    public <T> T readObject(String path, Class<T> clazz) {
        return toml.to(clazz);
    }

    @Override
    public <T> void saveObject(String path, T t) {
        var tomlWriter = new TomlWriter();
        try {
            tomlWriter.write(t, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}