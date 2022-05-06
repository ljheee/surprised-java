package com.ljheee.surprised.visibility;

import java.util.concurrent.TimeUnit;

/**
 * 不用volatile，如何跳出循环？
 * https://github.com/peteryuanpan/notebook/issues/101
 *
 * 1.进入循环时，调用某个同步方法；
 * 2.进入循环后，sleep若干时间(会让出CPU时间片)，完成write back.
 *
 * @author ljheee
 */
public class VisibilityAndCpuCache {

    public static boolean run = true;

    public static void main(String[] args) throws InterruptedException {


        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started.");
            int i = 0;
            while (run) {
                i++;

                // 能够跳出循环
                // println是synchronized方法，能够保证多线程在进入临界区之前，内存可见性
                // System.out.println("sync method: guarantee visibility before enter synchronized area.");


//                shortWait(1000);
                shortWait(20000);

                // sleep时，CPU空闲，完成主存回写；
                // CPU空闲 主存回写后，还需要当前线程重新从主存读取变量；
                //sleep，会让出CPU时间片，线程上下文切换（保存现场、恢复现场），因此会从主内存中重新读取flag，读到了false则跳出循环
                //注意：与sleep的时间没有关系，即使sleep 0ms，也会让出CPU时间片
//                try {
//                    Thread.sleep(0);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

            }
            System.out.println(Thread.currentThread().getName() + " break loop.");
        }, "subThread").start();

        // 主线程休眠，让出CPU，保证子线程已启动
        TimeUnit.SECONDS.sleep(1);

        run = false;
    }


    /**
     * Thread.sleep 会让出CPU
     * CPU空闲时，会进行主存回写等；
     *
     * @param millis
     */
    public static void sleepMillis(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 等待10微秒，太短了，缓存未失效，因此一直未从主内存中读取，一直是true
     * 等到20微秒，缓存失效了，因此会从主内存中读取，读到了false则跳出循环
     *
     * @param interval
     */
    public static void shortWait(long interval) {
        long start = System.nanoTime();
        long end;
        do {
            end = System.nanoTime();
        } while (start + interval >= end);
    }
}
