package com.ljheee.surprised.number;

/**
 * Integer 对象用作锁
 * synchronized(Integer)，在同步块修改Integer的值，此时每次锁住的都是不同的对象实例(valueOf()不在缓冲区的都产生新Integer)。
 * >127  <-128 不能锁住
 */
public class IntegerLock2 {

    private Integer integerLock = 500;

    ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public void getLock() {
        synchronized (integerLock) {
            threadLocal.set(System.currentTimeMillis());

            System.out.println("get integer lock!" + System.identityHashCode(integerLock));
            try {
                Thread.sleep(20000);
                integerLock++;
                System.out.println("used:" + (System.currentTimeMillis() - threadLocal.get()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        IntegerLock2 lock = new IntegerLock2();
        new Thread(() -> lock.getLock(), "ThreadIntegerLock1").start();
        new Thread(() -> lock.getLock(), "ThreadIntegerLock2").start();
        new Thread(() -> lock.getLock(), "ThreadIntegerLock3").start();

        // 三个线程使用的是不同的锁
    }
}
