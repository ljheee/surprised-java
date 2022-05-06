package com.ljheee.surprised.sync.blocking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


/**
 * ReentrantLock默认非公平锁情况下；
 * 加锁失败进入AQS阻塞队列，别人释放锁，再次获锁执行时，按进入队列的顺序出队(从head开始，每次唤醒一个)。
 *
 * @author ljheee
 */
public class EntryListWhenLockFail {

    static List<Thread> list = new ArrayList<>();
    static ReentrantLock lock = new ReentrantLock();


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " thread executed");
                try {
                    //这里的睡眠没有什么意义，仅仅为了控制台打印的时候有个间隔 视觉效果好
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }, "t" + i);

            list.add(t);
        }

        System.out.println("---启动顺序 调度顺序或者说获取锁的顺序讲道理是正序0--9----");

        lock.lock();
        try {
            for (Thread thread : list) {
                //这个打印主要是为了看到线程启动的顺序
                System.out.println(thread.getName() + "-启动顺序--正序0-9");
                thread.start();
                TimeUnit.MILLISECONDS.sleep(1);
            }
            System.out.println("------执行顺序--正序9-0");
        } finally {
            lock.unlock();
        }
    }


    // 执行结果
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
     * t0 thread executed
     * t1 thread executed
     * t2 thread executed
     * t3 thread executed
     * t4 thread executed
     * t5 thread executed
     * t6 thread executed
     * t7 thread executed
     * t8 thread executed
     * t9 thread executed
     */

}
