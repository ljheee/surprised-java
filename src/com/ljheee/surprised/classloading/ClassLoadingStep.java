package com.ljheee.surprised.classloading;

/**
 * main方法中首次使用单例，进行类加载（装载、链接、初始化）；
 * 链接-准备阶段，类字段赋初始值；
 * 初始化阶段，类字段赋正确的值；
 * <p>
 * 初始化时singleton先调用构造器（numB++），而后又给numB赋值0；
 */
public class ClassLoadingStep {

    public static void main(String[] args) {
        System.out.println("通过单例访问numA=" + Singleton.numA);
        System.out.println("通过单例访问numB=" + Singleton.numB);
    }
}

class Singleton {
    public static int numA = 0;

    private static Singleton singleton = new Singleton();

    private Singleton() {
        numA++;
        numB++;
        System.out.println("numA=" + numA);
        System.out.println("numB=" + numB);
    }

    public static int numB = 5;

    public static Singleton getInstance() {
        return singleton;
    }
}