package com.ljheee.surprised.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * https://www.cnblogs.com/zhizhizhiyuan/p/4966827.html
 * 有一点比较难理解的，是unpark操作可以再park操作之前。也就是说，先提供许可。
 * 当某线程调用park时，已经有许可了，它就消费这个许可，然后可以继续运行。这其实是必须的。
 * 考虑最简单的生产者(Producer)消费者(Consumer)模型：Consumer需要消费一个资源，于是调用park操作等待；
 * Producer则生产资源，然后调用unpark给予Consumer使用的许可。非常有可能的一种情况是，
 * Producer先生产，这时候Consumer可能还没有构造好（比如线程还没启动，或者还没切换到该线程）。
 * 那么等Consumer准备好要消费时，显然这时候资源已经生产好了，可以直接用，那么park操作当然可以直接运行下去。
 * 如果没有这个语义，那将非常难以操作。
 */
public class LockSupportPermit {


    public static void main(String[] args) throws InterruptedException {

        Thread currentThread = Thread.currentThread();


        // unpark先提供许可
        LockSupport.unpark(currentThread);

        Thread.sleep(1000L);

        // park 使用许可
        LockSupport.park();// 当 调用park时，已经有许可了，它就消费这个许可，然后可以继续运行。

        System.out.println("finish");//能正常执行
    }
}
