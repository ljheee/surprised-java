package com.ljheee.surprised.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * InheritableThreadLocal 在使用线程池时的缺陷
 */
public class ThreadLocalWithPool {


    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("1");

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        executorService.submit(() -> {
            System.out.println(threadLocal.get());  // 1 第一次打印1，是因为在父线程set(1)，使用InheritableThreadLocal 可使子线程获取到
        });

        threadLocal.set("2");// 更新是在父线程
        executorService.submit(() -> {
            System.out.println(threadLocal.get());  // 1 第二次打印1，是因为InheritableThreadLocal内部判断，当前ThreadLocal.ThreadLocalMap inheritableThreadLocals不为null，就不将父线程的copy过来
        });

        executorService.shutdown();
    }

    // InheritableThreadLocal 在使用线程池时的缺陷
    // 父线程中 创建子线程，创建时:子线程的inheritableThreadLocals是基于父线程的拷贝；但是此后父线程再更新本地变量，子线程持有的还是旧值
    // 父线程 后续threadLocal.set("2")更新值，如何同步更新给子线程

    // InheritableThreadLocal重写set(),除了调用super.set(),更新传给子线程?
    // 父线程的InheritableThreadLocal，持有子线程的InheritableThreadLocal
}
