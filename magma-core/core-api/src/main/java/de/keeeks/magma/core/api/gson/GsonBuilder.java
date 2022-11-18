package de.keeeks.magma.core.api.gson;

import com.google.gson.Gson;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.TypeAdapterFactory;
import de.keeeks.magma.core.api.gson.serializer.InstantSerializer;

public class GsonBuilder {
    private static final TypeAdapterFactory RECORD_TYPE_ADAPTER_FACTORY = new RecordGsonTypeAdapter();

    private static final Gson GLOBAL_GSON = buildGson();

    public static Gson buildGson() {
        return buildGson(
                new InstantSerializer()
        );
    }

    public static Gson buildGson(JsonSerializer<?>... serializers) {
        var gsonBuilder = new com.google.gson.GsonBuilder();
        for (JsonSerializer<?> serializer : serializers) {
            gsonBuilder.registerTypeAdapter(serializer.getType(), serializer);
        }
        gsonBuilder.registerTypeAdapterFactory(RECORD_TYPE_ADAPTER_FACTORY);
        gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
        gsonBuilder.setPrettyPrinting();
        return gsonBuilder.create();
    }

    public static Gson global() {
        return GLOBAL_GSON;
    }
}