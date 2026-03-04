package com.ljheee.surprised.classinitialization;

/**
 * Created by lijianhua04 on 2020/10/12.
 */
public class ParentSubClassTest {

    public static void main(String[] args) {


        ParentClass[] pc = new ParentClass[10];// 不会初始化
        ParentClass[] sc = new SubClass[10];// 不会初始化

        pc[0] = new SubClass();//ParentClass init、SubClass init
        sc[0] = new SubClass();//ParentClass init、SubClass init

    }
}
