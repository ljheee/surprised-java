package com.ljheee.surprised.sync;

/**
 * synchronized修饰类方法和普通方法的锁区别,获取类锁之后还能获取对象锁吗？
 * 不行，类锁不释放，对象锁阻塞
 */
public class ClassWithSync {

    public  static void originalStaticMethod() throws InterruptedException {
        System.out.println("originalStaticMethod");
    }

    public synchronized static void classLock() throws InterruptedException {
        System.out.println("执行类锁");
        Thread.sleep(10000);
    }

    public synchronized void instanceLock() {
        System.out.println("对象锁");
    }


    public static void main(String[] args) throws InterruptedException {

        ClassWithSync a = new ClassWithSync();

        // 先加类锁
        ClassWithSync.classLock();// a.classLock();也一样


        a.originalStaticMethod();
        new Thread(() -> {
            a.instanceLock();// 对象锁阻塞

        }).start();
    }

}
