package com.ljheee.surprised.object;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

/**
 * 虚引用
 * 必须和引用队列ReferenceQueue一起使用;
 * 虚引用所关联的对象被gc时，会添加到引用队列。
 */
public class PhantomReferenceTest {


    static List<byte[]> list = new ArrayList<>();
    static ReferenceQueue<UseNativeResourceObj> queue = new ReferenceQueue<>();


    public static void main(String[] args) throws InterruptedException {

        UseNativeResourceObj obj = new UseNativeResourceObj();
        PhantomReference<UseNativeResourceObj> phantomReference = new PhantomReference<>(obj, queue);


        new Thread(() -> {
            while (true) {
                Reference<? extends UseNativeResourceObj> poll = queue.poll();
                if (poll != null) {
                    System.out.println("虚引用对象被GC了:" + poll.get());//虚引用对象被GC了:null
                    break;
                }
            }
        }).start();

        System.out.println(phantomReference.get());//null

        Thread.sleep(1000);
        obj = null;
        System.gc();

        int time = 2;
        while (--time > 0) {
            list.add(new byte[1024 * 1024]);
        }

    }

}


class UseNativeResourceObj {
    final byte[] b = new byte[1024 * 1024];
}