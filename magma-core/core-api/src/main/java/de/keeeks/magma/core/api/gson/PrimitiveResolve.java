package de.keeeks.magma.core.api.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import java.time.Instant;
import java.util.UUID;

public interface PrimitiveResolve {
    default JsonPrimitive number(Number number) {
        return new JsonPrimitive(number);
    }

    default JsonPrimitive bool(boolean bool) {
        return new JsonPrimitive(bool);
    }

    default JsonPrimitive string(String s) {
        return new JsonPrimitive(s);
    }

    default JsonPrimitive character(Character character) {
        return new JsonPrimitive(character);
    }

    default JsonPrimitive instant(Instant instant) {
        return new JsonPrimitive(instant.toEpochMilli());
    }

    default JsonPrimitive uuid(UUID uuid) {
        return new JsonPrimitive(uuid.toString());
    }

    default Instant longToInstant(long millis) {
        return Instant.ofEpochMilli(millis);
    }

    default String elementToString(JsonElement element) {
        return element.getAsString();
    }

    default UUID elementToUuid(JsonElement element) {
        return UUID.fromString(element.getAsString());
    }
}