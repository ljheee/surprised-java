package com.ljheee.surprised.sync.blocking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * synchronized抢锁失败的线程，会进入 entryList 阻塞队列；
 * 在 entryList 中的线程，都是 blocking 状态；
 * <p>
 * 演示目标：
 * 1、证明 entryList 确实存在；
 * 2、entryList 里阻塞的线程，下次竞争锁的顺序是怎么样；
 *
 * @author ljheee
 */
public class EntryListWhenSyncFail {

    /**
     * 多线程启动，实际被(cpu调度)执行是乱序的；
     * thread:1 working
     * thread:4 working
     * thread:3 working
     * thread:2 working
     * thread:5 working
     */
    private static void multiThreadStartOutOfOrder() {
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " working");
            }, "thread:" + i).start();
        }
    }

    // ----------------------------------------------------------------------------------------
    // 证明 抢锁失败的线程，确实需要在某个集合中维护；下次获得锁时，需要按某个顺序选择。

    static List<Thread> list = new ArrayList<>();
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + " thread executed");
                    try {
                        //这里的睡眠没有什么意义，仅仅为了控制台打印的时候有个间隔 视觉效果好
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "t" + i);

            list.add(t);
        }

        System.out.println("---启动顺序 调度顺序或者说获取锁的顺序讲道理是正序0--9----");

        synchronized (lock) {
            for (Thread thread : list) {
                //这个打印主要是为了看到线程启动的顺序
                System.out.println(thread.getName() + "-启动顺序--正序0-9");
                thread.start();

                //这个睡眠相当重要，如果没有这个睡眠会有很大问题
                //这里是因为线程的start仅仅是告诉CPU线程可以调度了，但是会不会立马调度是不确定的
                //如果这里不睡眠 就有有这种情况出现
                //主线程执行t1.start--cpu没有调度t1--继续执行主线程t2-start cpu调度t2--然后再调度t1
                //虽然我们的启动顺序是正序的（t1--t2），但是调度顺序是错乱的  t2---t1

                TimeUnit.MILLISECONDS.sleep(1);
            }
            System.out.println("------执行顺序--正序9-0");
        }
    }




    /**
     * ---启动顺序 调度顺序或者说获取锁的顺序讲道理是正序0--9----
     * t0-启动顺序--正序0-9
     * t1-启动顺序--正序0-9
     * t2-启动顺序--正序0-9
     * t3-启动顺序--正序0-9
     * t4-启动顺序--正序0-9
     * t5-启动顺序--正序0-9
     * t6-启动顺序--正序0-9
     * t7-启动顺序--正序0-9
     * t8-启动顺序--正序0-9
     * t9-启动顺序--正序0-9
     * ------执行顺序--正序9-0
     * t9 thread executed
     * t8 thread executed
     * t7 thread executed
     * t6 thread executed
     * t5 thread executed
     * t4 thread executed
     * t3 thread executed
     * t2 thread executed
     * t1 thread executed
     * t0 thread executed
     */

}
