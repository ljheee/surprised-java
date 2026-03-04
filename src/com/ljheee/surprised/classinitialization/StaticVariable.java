package com.ljheee.surprised.classinitialization;

/**
 * 静态变量，声明在前，后面的static块中，可读可写；
 * 静态变量，声明语句在后，前面的static块中，只能写不能读；（出现illegal forward reference ）
 */
public class StaticVariable {

    public static int a = 0;
    public static int b = 0;

    static {

        a = 1;
        System.out.println(b);
        b = 2;
        c = 3;

        // 不能读变量c 编译不过，illegal forward reference
        //System.out.println(c);
    }

    public static int c = 0;


    public static void main(String[] args) {

    }


}
