package com.ljheee.surprised.classinitialization;


/**
 * 控制台打印：
 * ClassA initializing
 * ClassB initializing
 *
 * 之后，jstack两个线程都是RUNNABLE.
 *
 * 说明类加载初始化阶段是线程安全的，visualvm只能检测Java级别的锁，因此推断初始化阶段是加的jvm级别的锁
 */
public class InitializationDeadLock {

    public static void main(String[] args) {

        new Thread(() -> ClassA.test()).start();
        new Thread(() -> ClassB.test()).start();
    }
}

class ClassA {

    static {
        try {
            //初始化
            System.out.println("ClassA initializing");

            Thread.sleep(1);
        } catch (InterruptedException e) {
            //
        }

        new ClassB();
    }

    public static void test() {
        System.out.println("aaa");
    }
}

class ClassB {

    static {
        System.out.println("ClassB initializing");
        new ClassA();
    }

    public static void test() {
        System.out.println("bbb");
    }
}