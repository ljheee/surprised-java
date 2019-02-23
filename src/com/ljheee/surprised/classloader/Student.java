package com.ljheee.surprised.classloader;

/**
 * https://bbs.csdn.net/topics/390988734?list=lz
 */
public class Student {

    //静态常量，在编译时确定值 并存入此类的方法区静态常量池（在其他类中使用此值，不加载此类也能从常量池中取到）
    public static final String FIANL_STATIC_STRING = "【2】final修饰的静态常量";
    public static String STATIC_STRING = "【2】静态常量";

    static {
        System.out.println("【1】静态代码块");
    }

    public static void main(String[] args) {
        // final修饰的静态常量
        System.out.println(Student.FIANL_STATIC_STRING);

        // 静态常量
        //System.out.println(Student.STATIC_STRING);
    }

}

