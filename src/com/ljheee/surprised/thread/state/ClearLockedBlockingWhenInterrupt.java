package com.ljheee.surprised.thread.state;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * t2通过ReentrantLock#lock加锁失败，进入阻塞；
 * t2.interrupt(); 会使t2解阻塞；
 * <p>
 * 原先t2在park， 调用t2.interrupt()之后其实是唤醒了t2;
 * 只是t2处于lock()方法内部的for(;;)死循环中，加锁失败又被park了
 */
public class ClearLockedBlockingWhenInterrupt {


    static Thread t0 = null;
    static Thread t1 = null;
    static Thread t2 = null;

    static Lock lock = new ReentrantLock();


    public static void main(String[] args) throws InterruptedException {

        t1 = new Thread(() -> {
            // lock, and never release.
            lock.lock();
        });
        t1.setName("t1");
        t1.start();

        // guarantee t1 has startup
        TimeUnit.SECONDS.sleep(1);

        t2 = new Thread(() -> {
            // try lock, but blocking
            // java.lang.Thread.State: WAITING (parking)
            lock.lock();

            // continue doing,when un blocking
            System.out.println("t2, un blocking.");
        });
        t2.setName("t2");
        t2.start();


        t0 = new Thread(() -> {
            while (true) {

                // always check t2 Thread.State
                if (t2.getState() != Thread.State.WAITING) {
                    System.out.println("monitor:" + t2.getState());
                }
            }
        });
        t0.setDaemon(true);
        t0.setName("t0");
        t0.start();


        TimeUnit.SECONDS.sleep(6);
        t2.interrupt();
        t0.join();

    }

}
