package com.ljheee.surprised.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 线程管理接口
 */
public class ProcessMain {


    /**
     * main线程，启动的6个线程。
     *
     * 6:Monitor Ctrl-Break
     * 5:Attach Listener
     * 4:Signal Dispatcher
     * 3:Finalizer
     * 2:Reference Handler
     * 1:main
     *
     * @param args
     */
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadId() + " " + threadInfo.getThreadName());
        }
    }


}
