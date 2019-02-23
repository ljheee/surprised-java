package com.ljheee.surprised.classloader;


/**
 * Student静态代码块就不会被执行
 * 原因参考 https://blog.csdn.net/henglang5194/article/details/78035959
 */
public class FinalStaticTest {
    // 测试时候，注释分开去掉才能看到效果
    public static void main(String[] args) {
        // final修饰的静态常量
//        System.out.println(Student.FIANL_STATIC_STRING);
        System.out.println(Student.STATIC_STRING);

        // 静态常量
        // System.out.println(Student.STATIC_STRING);
    }
}
