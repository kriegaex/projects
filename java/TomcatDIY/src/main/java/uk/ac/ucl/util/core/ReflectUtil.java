package uk.ac.ucl.util.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ReflectUtil aims to handle reflection and its exception
 */
public class ReflectUtil {
    /**
     * Return a instance of the given class name
     * This method only works for classes which have no parameters in their constructors.
     * In other words, this method does not check the number and the types of parameters.
     * @param className
     * @return
     */
    public static Object getInstance(String className) {
        Class<?> servletObject = null;
        try {
            servletObject = Class.forName(className);
            Constructor<?> constructor = servletObject.getConstructor();
            Object object = constructor.newInstance();
            return object;
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e ) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This methods invokes given method name in the given instance.
     * Same as the previous method, it does not check the number and type of parameters
     * @param object
     * @param method
     * @param request
     * @param response
     */
    public static void invoke(Object object, String method,
                              HttpServletRequest request, HttpServletResponse response) {
        Method doGet = null;
        try {
            doGet = object.getClass().getMethod(method,
                    HttpServletRequest.class, HttpServletResponse.class);
            doGet.invoke(object, request, response);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
