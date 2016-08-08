package tconfig;

import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TConfigFactory {
    private static final String SUFFIX = "$tconfig";

    @SneakyThrows({ClassNotFoundException.class, InstantiationException.class, IllegalAccessException.class, InvocationTargetException.class})
    public static <T> T createConfigFor(Class<T> type) {
        final ClassLoader classLoader = type.getClassLoader();
        final String typeName = type.getName();
        Class<? extends T> configClass = Class.forName(typeName + SUFFIX, true, classLoader).asSubclass(type);
        final Constructor<? extends T> constructor;
        try {
            constructor = configClass.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Class " + configClass + " has no matching constructor");
        }
        return constructor.newInstance();
    }

}
