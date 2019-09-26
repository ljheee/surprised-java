package com.ljheee.surprised.threadlocal;

import java.lang.ref.Reference;
import java.lang.reflect.Field;

/**
 * ThreadLocal 为什么会造成内存泄露
 */
public class ThreadLocalOOM {


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        setThreadLocal();   // main线程执行完此方法，方法局部变量threadLocal就结束被回收了
                            // key（threadLocal）是弱引用，被回收了，而value还存在内存
                            // key为null了，我们不能获取到value，但value却一直在内存。

        for (int i = 0; i < 100; i++) {
            byte[] bytes = new byte[1024 * 1024];// appear gc
        }
        printThreadLocal(Thread.currentThread());
    }

    private static ThreadLocal<String> setThreadLocal() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("localVar");
        return threadLocal;
    }

    /**
     * 打印thread.threadLocals所有本地变量
     *
     * @param thread
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private static void printThreadLocal(Thread thread) throws NoSuchFieldException, IllegalAccessException {
        Class<? extends Thread> threadClass = thread.getClass();

        // 获取 Thread.threadLocals字段
        Field threadLocalsField = threadClass.getDeclaredField("threadLocals");
        threadLocalsField.setAccessible(true);
        Object threadLocalMapValue = threadLocalsField.get(thread);// threadLocals的value，是个ThreadLocalMap

        Class<?> aClass = threadLocalMapValue.getClass();// 获取ThreadLocalMap.table
        Field tableField = aClass.getDeclaredField("table");
        tableField.setAccessible(true);
        Object[] entrys = (Object[]) tableField.get(threadLocalMapValue);//hreadLocalMap.table的value，是个Entry[]

        for (Object entry : entrys) {
            if (entry == null) continue;
            Class<?> entryClass = entry.getClass();
            Field valueField = entryClass.getDeclaredField("value");
            valueField.setAccessible(true);
            Object value = valueField.get(entry);

            if (value != null) {
                Field referentField = Reference.class.getDeclaredField("referent");
                referentField.setAccessible(true);
                Object key = referentField.get(entry);
                System.out.println("key=" + key + ", value=" + value);
            }
        }
    }


}
