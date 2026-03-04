package com.ljheee.surprised.vm;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;

/**
 * 对象会直接分配到老年代
 * https://juejin.im/post/5d1b048ff265da1bb67a326e
 */
public class HeapSize {

    public static void main(String[] args) {
        byte[] array = new byte[700 * 1024 * 1024];//734,003,200　　
        for (MemoryPoolMXBean memoryPoolMXBean : ManagementFactory.getMemoryPoolMXBeans()) {
            System.out.println(memoryPoolMXBean.getName() + "总量:" + memoryPoolMXBean.getUsage().getCommitted()
                    + " 使用的内存:" + memoryPoolMXBean.getUsage().getUsed());
        }
    }
}
