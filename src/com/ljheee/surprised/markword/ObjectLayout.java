package com.ljheee.surprised.markword;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * 借助 jol-core （openjdk的工具） 查看对象大小和布局
 */
public class ObjectLayout {

    public static void main(String[] args) {

        Aclass a = new Aclass();

//        String s = ClassLayout.parseClass(Aclass.class).toPrintable();
//        System.out.println(s);

        System.out.println(VM.current().details());
        System.out.println(VM.current().sizeOf(a));// 查看对象大小，返回对象字节数；类似于C++的sizeOf()
        System.out.println("===============");
        String s1 = ClassLayout.parseInstance(a).toPrintable();// jol-core 0.9 版本的新API，可以查看object header的value
        System.out.println(s1);


        System.out.println(Math.pow(2, 16));
        System.out.println((1 << 16));//65536
    }

}
