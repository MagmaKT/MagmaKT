package de.keeeks.magma.configuration.json;

import com.google.gson.Gson;
import de.keeeks.magma.configuration.api.Configuration;
import de.keeeks.magma.core.api.gson.GsonBuilder;
import de.keeeks.magma.core.api.gson.JsonSerializer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonConfiguration extends Configuration {

    private Gson gson = GsonBuilder.global();

    public JsonConfiguration(String path, String fileName) {
        super(path, fileName, ConfigurationType.JSON);
    }

    public <T> JsonConfiguration withSerializer(JsonSerializer<T> jsonSerializer) {
        gson = GsonBuilder.buildGson(jsonSerializer);
        return this;
    }

    @Override
    public <T> T readObject(Class<T> clazz) {
        try (FileReader fileReader = new FileReader(file)) {
            return gson.fromJson(fileReader, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> void saveObject(T t) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            gson.toJson(t, fileWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonConfiguration create(String path, String fileName) {
        return new JsonConfiguration(path, fileName);
    }
}