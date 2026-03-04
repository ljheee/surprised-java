package com.ljheee.surprised.padding;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class DoubleEndedPaddingWithArray {

    int a = 10;
    static int[] arr = {0, 1, 2};
    int b = 10;


    public static void main(String[] args) {
        DoubleEndedPaddingWithArray doubleEndedPaddingWithArray = new DoubleEndedPaddingWithArray();
        //System.out.println(ClassLayout.parseInstance(doubleEndedPaddingWithArray).toPrintable());

        // 开启指针压缩，Instance size: 32 bytes=8+4+4+4*3
        // 关闭指针压缩，Instance size: 40 bytes
        System.out.println(ClassLayout.parseInstance(arr).toPrintable());

        new ReentrantLock();
    }
}
