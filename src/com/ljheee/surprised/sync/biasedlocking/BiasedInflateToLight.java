package com.ljheee.surprised.sync.biasedlocking;

import org.openjdk.jol.info.ClassLayout;

/**
 * 一致性hashCode对偏向锁的影响
 * hashCode()一定会引起锁膨胀：
 * 在synchronized同步块之外执行hashCode()，在未发生锁争抢的情况，依然进行了锁升级(偏向->轻量级)。
 * 在synchronized同步块内执行hashCode()，直接膨胀到重量级。
 */
public class BiasedInflateToLight {

    public static void main(String[] args) {

        Object lock = new Object();

        // 锁状态位：01
        System.out.println("未加锁:" + ClassLayout.parseInstance(lock).toPrintable());

        lock.hashCode();

        synchronized (lock){

            // 锁状态位：01
            System.out.println("偏向锁:" + ClassLayout.parseInstance(lock).toPrintable());
        }

    }

}
