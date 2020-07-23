package uk.ac.ucl.classLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class CustomizedURLClassLoader extends URLClassLoader {
    public CustomizedURLClassLoader(URL[] urls) {
        super(urls);
    }

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        URL url = new URL("file:/Users/chaozy/Desktop/CS/" +
                "projects/java/TomcatDIY/jar_4_test/test.jar");

        URL[] urls = new URL[] {url};
        CustomizedURLClassLoader urlClassLoader = new CustomizedURLClassLoader(urls);
        Class<?> how2jClass = urlClassLoader.loadClass("cn.how2j.diytomcat.test.HOW2J");

        Object o = how2jClass.newInstance();

        Method m = how2jClass.getMethod("hello");
        m.invoke(o);
        System.out.println(how2jClass.getClassLoader());
    }

}
