package com.ljheee.surprised.object;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 弱引用
 * 弱引用可以和一个引用队列（ReferenceQueue）联合使用，
 * 如果弱引用所引用的对象被垃圾回收，Java虚拟机就会把这个弱引用加入到与之关联的引用队列中。
 *
 * 弱引用：可用于监听对象何时被回收
 * 引用和引用队列提供了一种通知机制，允许我们知道对象已经被销毁或者即将被销毁。
 */
public class WeakReferenceTest {


    static List<byte[]> list = new ArrayList<>();
    static ReferenceQueue<Object> queue = new ReferenceQueue<>();


    public static void main(String[] args) throws InterruptedException {

        Object obj = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(obj, queue);


        new Thread(() -> {
            while (true) {
                Reference<? extends Object> poll = queue.poll();
                if (poll != null) {
                    System.out.println("弱引用对象被GC了:" + poll.get());//弱引用对象被GC了:null
                    break;
                }
            }
        }).start();

        System.out.println(weakReference.get());//java.lang.Object@15aeb7ab

        Thread.sleep(1000);
        obj = null;
        System.gc();
        System.out.println(weakReference.get());//null

        int time = 2;
        while (--time > 0) {
            list.add(new byte[1024 * 1024]);
        }

    }

}
