package com.ljheee.surprised.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * 打印对象布局，验证偏向锁
 * 1、启动后睡眠4s+
 * 2、关闭偏向延迟
 */
public class BiasedLockingStartupDelay {

    public static void main(String[] args) throws InterruptedException {

        // 锁标记是 00 ，轻量级锁
        Object newObject = new Object();
        synchronized (newObject){
            System.out.println(ClassLayout.parseInstance(newObject).toPrintable());
        }

        Thread.sleep(5000);

        // 锁标记是 101，偏向锁，此时它是 匿名偏向
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        // 锁标记是 101，偏向锁
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

    }
}
