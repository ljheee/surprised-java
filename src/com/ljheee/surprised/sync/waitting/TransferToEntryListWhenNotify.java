package com.ljheee.surprised.sync.waitting;


import java.util.concurrent.TimeUnit;

/**
 * 实验目标
 * 调用notify，并非直接唤醒线程；而是将线程从waitSet转移到entryList；
 *
 * @author ljheee
 */
public class TransferToEntryListWhenNotify {

    static Object object = new Object();//锁对象
    static boolean isWoman = false; // 是否有女人

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (object) {
                while (!isWoman) {//判断是否有女人
                    System.out.println("没有女人 我去等待老板安排 先休息，安排好之后叫醒我");
                    try {
                        //jack线程进入阻塞，但是释放了锁
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("开始工作啪啪啪啪");
            }
        }, "jack").start();


        TimeUnit.SECONDS.sleep(1);

        System.out.println("1s之后主线程获取锁");
        System.out.println("因为jack wait释放了锁，主线程能够获取到");
        System.out.println("-----------关键看打印顺序----------------");
        synchronized (object) {

            // place 1
            for (int i = 0; i < 5; i++) {
                new Thread(() -> {
                    synchronized (object) {
                        System.out.println("那些默默无闻的程序员coding");
                    }
                }, "t" + i).start();
                TimeUnit.MILLISECONDS.sleep(1);
            }

            // place 2
            isWoman = true;
            object.notifyAll();
        }


    }

}
