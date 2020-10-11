package com.ljheee.surprised.classloader.namespace;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;

/**
 * ClassLoader命名空间
 *
 * findLoadedClass()底层是按className+classloader作为key缓存到Dictionary(字典缓存)。
 * jvm中：classloader命名空间+全限定类名，唯一确定一份类加载；
 *
 * 实验：实验两个classloader加载相同的类，之间不能强转；
 */
public class FileSystemClassLoader extends ClassLoader {


    String loadPath;

    public FileSystemClassLoader(ClassLoader parent) {
        super(parent);
    }

    public FileSystemClassLoader() {
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] bytes = loadClassData(name);
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] loadClassData(String name) {

        // ./classes/com/ljheee/surprised/classloading/Person.class
        name = "./classes/" + name.replaceAll("\\.", "\\/") + ".class";

        File file = new File(name);
        byte[] array = new byte[0];
        try {
            array = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return array;
    }

    public static void main(String[] args) throws Exception {

        /**
         * loader1加载Person的时候通过向上委托一直到启动类加载器然后又往下到应用类加载器都无法加载，只能通过loader1加载器来加载，
         * loader1加载的MyPerson的class对象clazz1属于loader1的命名空间，同样的道理clazz2属于loader2的命名空间;
         *
         * 可见：
         * 一个JAVA程序可以多次装载具有同一个全限定名的类型，仅仅通过 全路径类名，是不能保证该类型在JVM中的唯一性。
         * 于是JVM引入了“运行时包”的概念，类型的区分，不是通过全限定类名，还加上了classloader的命名空间。
         *
         */
        FileSystemClassLoader fileSystemClassLoader = new FileSystemClassLoader();
        Class<?> class1 = fileSystemClassLoader.loadClass("com.ljheee.surprised.classloader.namespace.Person");

        FileSystemClassLoader fileSystemClassLoader2 = new FileSystemClassLoader();
        Class<?> class2 = fileSystemClassLoader2.loadClass("com.ljheee.surprised.classloader.namespace.Person");

        //删除target/out下 IDE编译生成的Person.class。不然父加载器class sun.misc.Launcher$AppClassLoader.loadClass会找到类路径下的
        System.out.println(class1.getClassLoader());

        System.out.println(class1);
        System.out.println(class2);
        System.out.println(class1 == class2);


        //java.lang.ClassCastException: com.ljheee.surprised.classloader.namespace.Person cannot be cast to com.ljheee.surprised.classloader.namespace.Person
        Method setParent = class1.getDeclaredMethod("setPerson", Object.class);
        setParent.invoke(class1.newInstance(), class2.newInstance());

    }
}
