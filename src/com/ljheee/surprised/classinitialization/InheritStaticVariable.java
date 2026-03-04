package com.ljheee.surprised.classinitialization;

/**
 * 控制台打印：
 * Parent initializing
 * ppp
 * <p>
 * 子类继承的static变量，直接访问父类；
 * 子类：只会加载，不会初始化。
 */
public class InheritStaticVariable {

    public static void main(String[] args) {
        System.out.println(Sub.str);
    }

}

class Parent {
    public static String str = "ppp";

    static {
        System.out.println("Parent initializing");
    }
}

class Sub extends Parent {
    static {
        //str += "a"; //加上这行也不影响结果
        System.out.println("Sub initializing");
    }
}