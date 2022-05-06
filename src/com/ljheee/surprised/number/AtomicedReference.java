package com.ljheee.surprised.number;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicedReference
 * 引用传递，内部方法改动对背调方有影响
 */
public class AtomicedReference {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);


        plusOne(atomicInteger);
        plusOne(atomicInteger);
        plusOne(atomicInteger);
        System.out.println(atomicInteger.get());// 3

    }


    public static void plusOne(AtomicInteger atomicInteger) {
        atomicInteger.incrementAndGet();
    }

}
