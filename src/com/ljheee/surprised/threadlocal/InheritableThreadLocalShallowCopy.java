package com.ljheee.surprised.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * InheritableThreadLocal 在使用线程池时的缺陷
 */
public class InheritableThreadLocalShallowCopy {


    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("1");

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(() -> {
            System.out.println(threadLocal.get());  // 1 第一次打印1

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadLocal.get());  // 1 第二次打印1
        });

        threadLocal.set("2");// 更新是在父线程
        System.out.println("父线程" + threadLocal.get());// 父线程2

        executorService.shutdown();
    }

}
