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
     *"Low Memory Detector" 负责对可使用内存进行检测，如果发现可用内存低，分配新的内存空间。
     *"CompilerThread0" 用来调用JITing，实时编译装卸class。
     *"Signal Dispatcher" 负责分发内部事件。
     *"Finalizer" 负责调用Finalizer方法。
     *"Reference Handler" 负责处理引用。
     *"VM Thread"， "VM Periodic Task Thread"从名字上看是虚机内部线程。
     *"main" 是主线程。
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
