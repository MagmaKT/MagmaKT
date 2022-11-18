package de.keeeks.magma.core.api.gson.serializer;

import com.google.gson.JsonObject;
import de.keeeks.magma.core.api.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.Instant;

public class InstantSerializer extends JsonSerializer<Instant> {

    public InstantSerializer() {
        super(Instant.class);
    }

    @Override
    public void write(JsonObject jsonElement, Type type, Instant object) {
        jsonElement.addProperty("time", object.toEpochMilli());
    }

    @Override
    public Instant read(JsonObject element, Type type) {
        return Instant.ofEpochMilli(element.get("time").getAsLong());
    }
}