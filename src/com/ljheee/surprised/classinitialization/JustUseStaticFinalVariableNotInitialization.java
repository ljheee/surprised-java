package com.ljheee.surprised.classinitialization;

import java.util.UUID;

/**
 * @author lijianhua.
 */
public class JustUseStaticFinalVariableNotInitialization {

    public static void main(String[] args) {
        System.out.println(Test.parentAge);

    }
}

class Test {
    public static final int parentAge = 2;
    //public static final String str = UUID.randomUUID().toString();//如果静态常量需要计算，运行时才知道，就得初始化类

    static {
        System.out.println("Test init");
    }
}