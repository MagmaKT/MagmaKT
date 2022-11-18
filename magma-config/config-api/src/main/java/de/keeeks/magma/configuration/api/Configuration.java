package de.keeeks.magma.configuration.api;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

import java.io.File;

public abstract class Configuration {

    protected final File file;

    public Configuration(String path, String fileName, ConfigurationType configurationType) {
        this.file = createFile("magma-kt/config/" + path, fileName, configurationType);
    }

    public abstract <T> T readObject(Class<T> clazz);

    public final <T> T readObject(Class<T> clazz, T defaultType) {
        var object = readObject(clazz);
        if (object != null) {
            return object;
        }
        saveObject(defaultType);
        return defaultType;
    }

    public abstract <T> void saveObject(T t);

    @SneakyThrows
    private File createFile(String path, String fileName, ConfigurationType configurationType) {
        var configurationFile = new File(path, fileName + configurationType.fileEnding());
        if (!configurationFile.getParentFile().exists()) {
            configurationFile.getParentFile().mkdirs();
        }
        if (!configurationFile.exists()) {
            configurationFile.createNewFile();
        }
        return configurationFile;
    }

    @Getter
    @Accessors(fluent = true)
    public static enum ConfigurationType {
        JSON(".json"),
        TOML(".toml"),
        YAML(".yml");

        private final String fileEnding;

        ConfigurationType(String fileEnding) {
            this.fileEnding = fileEnding;
        }
    }
}