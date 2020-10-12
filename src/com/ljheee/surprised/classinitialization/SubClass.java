package com.ljheee.surprised.classinitialization;

/**
 * Created by lijianhua04 on 2020/10/12.
 */
public class SubClass extends ParentClass {

    public static int childAge = 0;

    static {
        childAge = 9;
        System.out.println("SubClass init");
    }
}
