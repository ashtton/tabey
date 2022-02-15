package me.gleeming.tabey.reflection;

import lombok.SneakyThrows;
import org.bukkit.Bukkit;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Reflection {

    // The NMS version the server is running
    public static String NMS_VERSION = Bukkit.getServer().getClass().getPackage().getName().substring(23);

    /**
     * Gets a specified class
     * @param name Class name
     * @return Class
     */
    @SneakyThrows
    public static Class<?> getClass(String name) {
        return Class.forName(name);
    }

    /**
     * Sets a field on an object
     * @param object Object
     * @param fieldName Field name
     * @param value Value
     */
    @SneakyThrows
    public void setField(Object object, String fieldName, Object value) {
        Field field = object.getClass().getField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
    }

    /**
     * Sets a field on an object
     * @param object Object
     * @param fieldName Field name
     * @param value Value
     */
    @SneakyThrows
    public void setDeclaredField(Object object, String fieldName, Object value) {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
    }


    /**
     * Gets a field on an object
     * @param object Object
     * @param fieldName Field name
     */
    @SneakyThrows
    public Object getField(Object object, String fieldName) {
        Field field = object.getClass().getField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }

    /**
     * Calls a specified method
     * @param object Object
     * @param method Method
     * @return Method Return
     */
    @SneakyThrows
    public static Object callMethod(Object object, String method, Object... params) {
        List<Method> methodList = Arrays.stream(object.getClass().getMethods())
                .filter(m -> m.getName().equals(method))
                .filter(m -> m.getParameters().length == params.length)
                .collect(Collectors.toList());

        return methodList.get(0).invoke(object, params);
    }

    /**
     * Calls a specified method
     * @param object Object
     * @param method Method
     * @return Method Return
     */
    @SneakyThrows
    public static Object callMethod(Class<?> object, String method, Object... params) {
        List<Method> methodList = Arrays.stream(object.getDeclaredMethods())
                .filter(m -> m.getName().equals(method))
                .collect(Collectors.toList());

        return methodList.get(0).invoke(object, params);
    }


    /**
     * Calls a specified method
     * @param object Object
     * @param method Method
     * @return Method Return
     */
    @SneakyThrows
    public static Object callMethod(Object object, String method, Class<?>[] classes, Object... params) {
        return object.getClass().getMethod(method, classes).invoke(object, params);
    }

    /**
     * Initializes a specified class
     * @param c Class
     * @param params Params
     * @return Initialized class
     */
    @SneakyThrows
    public static Object initialize(Class<?> c, Object... params) {
        List<Constructor<?>> constructorList = Arrays.stream(c.getConstructors())
                .filter(constructor -> constructor.getParameters().length == params.length)
                .collect(Collectors.toList());

        return constructorList.get(0).newInstance(params);
    }

}