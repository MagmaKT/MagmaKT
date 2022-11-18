package de.keeeks.magma.core.api.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * code from https://github.com/google/gson/issues/1794
 * took me very long to copy all the code from the comments lol
 */
public class RecordGsonTypeAdapter implements TypeAdapterFactory {

    private static final Map<Class<?>, Object> PRIMITIVE_DEFAULTS = new HashMap<>() {{
        put(byte.class, (byte) 0);
        put(int.class, 0);
        put(long.class, 0L);
        put(short.class, (short) 0);
        put(double.class, 0D);
        put(float.class, 0F);
        put(char.class, '\0');
        put(boolean.class, false);
    }};

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

        @SuppressWarnings("unchecked")
        var clazz = (Class<T>) type.getRawType();
        if (!clazz.isRecord()) {
            return null;
        }

        var delegate = gson.getDelegateAdapter(this, type);
        return new TypeAdapter<>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            @Override
            public T read(JsonReader reader) throws IOException {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return null;
                } else {
                    var recordComponents = clazz.getRecordComponents();
                    var typeMap = new HashMap<String, TypeToken<?>>();
                    for (int i = 0; i < recordComponents.length; i++) {
                        typeMap.put(recordComponents[i].getName(), TypeToken.get(recordComponents[i].getGenericType()));
                    }
                    var argsMap = new HashMap<String, Object>();
                    reader.beginObject();
                    while (reader.hasNext()) {
                        var name = reader.nextName();
                        if (typeMap.containsKey(name)) {
                            argsMap.put(name, gson.getAdapter(typeMap.get(name)).read(reader));
                        } else {
                            gson.getAdapter(Object.class).read(reader);
                        }
                    }
                    reader.endObject();

                    var argTypes = new Class<?>[recordComponents.length];
                    var args = new Object[recordComponents.length];
                    for (int i = 0; i < recordComponents.length; i++) {
                        argTypes[i] = recordComponents[i].getType();
                        var name = recordComponents[i].getName();
                        var value = argsMap.get(name);
                        var type = typeMap.get(name);
                        if (value == null && (type != null && type.getRawType().isPrimitive())) {
                            value = PRIMITIVE_DEFAULTS.get(type.getRawType());
                        }
                        args[i] = value;
                    }

                    return constructTInstance(argTypes, args);
                }
            }

            private T constructTInstance(Class<?>[] argTypes, Object[] args) {
                try {
                    var constructor = clazz.getDeclaredConstructor(argTypes);
                    constructor.setAccessible(true);
                    return constructor.newInstance(args);
                } catch (NoSuchMethodException | InstantiationException | SecurityException | IllegalAccessException |
                         IllegalArgumentException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}