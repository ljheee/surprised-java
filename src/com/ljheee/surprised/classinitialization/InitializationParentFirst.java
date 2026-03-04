package com.ljheee.surprised.classinitialization;


/**
 * 先初始化父类
 */
public class InitializationParentFirst {

    public static void main(String[] args) {
        System.out.println(SubClass1.a);
    }

}


class SubClass1 extends Parent {

    public static int a = 1;
    static {
        System.out.println("Sub initializing");
    }
}