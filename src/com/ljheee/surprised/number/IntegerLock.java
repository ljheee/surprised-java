package com.ljheee.surprised.number;

/**
 * Integer 对象用作锁
 * 如果只是synchronized(Integer)，作为锁对象，不在同步块修改Integer的值，完全没问题，和缓存范围无关，
 * 用作锁对象的Integer的值是在缓存范围还是不在，都没影响。
 * >127  <-128 不能锁住
 */
public class IntegerLock {

    private Integer integerLock = 150;

    ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public void getLock() {
        synchronized (integerLock) {
            threadLocal.set(System.currentTimeMillis());

            System.out.println("get integer lock!" + System.identityHashCode(integerLock));
            try {
                Thread.sleep(20000);
                System.out.println("used:" + (System.currentTimeMillis() - threadLocal.get()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        IntegerLock lock = new IntegerLock();
//        lock.getLock();

        new Thread(() -> lock.getLock(), "ThreadIntegerLock1").start();
        new Thread(() -> lock.getLock(), "ThreadIntegerLock2").start();
        new Thread(() -> lock.getLock(), "ThreadIntegerLock3").start();

        //总用时60s 说明三个线程使用的是同一把锁
    }
}
