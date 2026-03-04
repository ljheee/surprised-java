package com.ljheee.surprised.classinitialization;

/**
 * Created by lijianhua04 on 2020/10/12.
 */
public class ParentClass {

    public static int parentAge = 0;

    static {
        parentAge = 100;
        System.out.println("ParentClass init");
    }


}
