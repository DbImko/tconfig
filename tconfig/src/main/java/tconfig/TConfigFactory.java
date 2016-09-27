package tconfig;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class TConfigFactory {
    private static final String SUFFIX = "$tconfig";

    public static <T> T createConfigFor(Class<T> type) {
        return AccessController.doPrivileged(new PrivilegedAction<T>() {
            @Override
            public T run() {
                final ClassLoader classLoader = type.getClassLoader();
                final String typeName = type.getName();
                Class<? extends T> configClass;
                String configClassName = typeName + SUFFIX;
                try {
                    configClass = Class.forName(configClassName, true, classLoader).asSubclass(type);
                } catch (ClassNotFoundException e) {
                    throw new IllegalArgumentException(configClassName + " not found", e);
                }
                final Constructor<? extends T> constructor;
                try {
                    constructor = configClass.getConstructor();
                } catch (NoSuchMethodException e) {
                    throw new IllegalArgumentException("Class " + configClass + " has no matching constructor");
                }
                try {
                    return constructor.newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new IllegalArgumentException("Configuration implementation " + configClass + " could not be instantiated", e);
                }
            }
        });
    }
}
