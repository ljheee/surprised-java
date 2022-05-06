package com.ljheee.surprised.thread.state;

import java.util.concurrent.TimeUnit;

/**
 * synchronized获取锁失败、阻塞后，不能响应中断；
 * （即使调用t.interrupt()线程还是阻塞）
 */
public class SynchronizedNotCareInterrupt {


    static Thread t1 = null;
    static Thread t2 = null;

    static Object lock = new Object();


    public static void main(String[] args) throws InterruptedException {


        t1 = new Thread(() -> {

            // lock, and never release.
            synchronized (lock) {
                try {
                    TimeUnit.HOURS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.setName("t1");
        t1.start();

        // guarantee t1 has startup
        TimeUnit.SECONDS.sleep(1);

        t2 = new Thread(() -> {

            // try lock, but blocking
            // java.lang.Thread.State: BLOCKED (on object monitor)
            synchronized (lock) {
                System.out.println("t2 doing.");
            }
        });
        t2.setName("t2");
        t2.start();


        TimeUnit.SECONDS.sleep(6);
        t2.interrupt();
        t1.join();

    }


}
