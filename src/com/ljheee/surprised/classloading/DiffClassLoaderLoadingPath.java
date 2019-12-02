package com.ljheee.surprised.classloading;

import java.util.Arrays;
import java.util.List;

/**
 * 不同类加载器的加载路径
 */
public class DiffClassLoaderLoadingPath {


    public static void main(String[] args) {
        bootClassLoaderLoadingPath();
        extClassLoaderLoadingPath();
        appClassLoaderLoadingPath();
    }




    private static void bootClassLoaderLoadingPath() {
        String property = System.getProperty("sun.boot.class.path");
        List<String> list = Arrays.asList(property.split(";"));
        System.out.println("bootClassLoader");
        for (String s: list){
            System.out.println(s);
        }
    }


    private static void extClassLoaderLoadingPath() {
        String property = System.getProperty("java.ext.dirs");
        List<String> list = Arrays.asList(property.split(";"));
        System.out.println("extClassLoader");
        for (String s: list){
            System.out.println(s);
        }
    }

    private static void appClassLoaderLoadingPath() {
        String property = System.getProperty("java.class.path");
        List<String> list = Arrays.asList(property.split(";"));
        System.out.println("appClassLoader");
        for (String s: list){
            System.out.println(s);
        }
    }
}
