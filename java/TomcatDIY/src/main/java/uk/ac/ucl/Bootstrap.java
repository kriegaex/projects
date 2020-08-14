package uk.ac.ucl;

import uk.ac.ucl.classLoader.CommonClassLoader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Bootstrap {
    public static void main(String[] args)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        // There are two ways of starting the server

        // FIRST: directly calling the start() method in class Server
//        Server server = new Server();
//        server.start();

        // SECOND: Loading the Server class through CommonClassLoader,
        // then call the method through reflection
        CommonClassLoader commonClassLoader = new CommonClassLoader();
        // TODO : EXPLAIN this line of code
        Thread.currentThread().setContextClassLoader(commonClassLoader);

        String serverClassName = "uk.ac.ucl.bean.conf.Server";
        Class<?> serverClass = commonClassLoader.loadClass(serverClassName);
        Constructor<?> constructor = serverClass.getConstructor();
        Object serverObject = constructor.newInstance();
        Method m = serverClass.getMethod("start");
        m.invoke(serverObject);
    }

}
