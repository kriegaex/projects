package uk.ac.ucl.classLoader;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;

public class CustomizedClassLoader extends ClassLoader {
    private File classFolder = new File(System.getProperty("user.dir"),
            "classes_4_test");

    protected Class<?> findClass(String qualifiedName) throws ClassNotFoundException {
        byte[] data = new byte[0];
        try {
            data = loadClassData(qualifiedName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Converts an array of bytes into an instance of class Class.
        return defineClass(qualifiedName, data, 0, data.length);
    }

    private byte[] loadClassData(String fullQualifiedName) throws ClassNotFoundException, IOException {
        String fileName = fullQualifiedName.replace(".", "/") +".class";

        File classFile = new File(classFolder, fileName);
        if (!classFile.exists()){
            throw new ClassNotFoundException(fullQualifiedName);
        }
        return Files.readAllBytes(classFile.toPath());
    }

    public static void main(String[] args)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        CustomizedClassLoader loader = new CustomizedClassLoader();
        Class<?> how2jClass = loader.loadClass("cn.how2j.diytomcat.test.HOW2J");

        Object o = how2jClass.newInstance();

        Method m = how2jClass.getMethod("hello");
        m.invoke(o);
        System.out.println(how2jClass.getClassLoader());
    }

}
