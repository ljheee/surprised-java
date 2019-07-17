package com.ljheee.surprised.innerclass;

/**
 * Created by lijianhua04 on 2019/7/16.
 */
public class Main {

    public static void main(String[] args) {
        Outer outer = new Outer();      //此刻其内部类是否也会被加载？
        System.out.println("===========分割线===========");
//        Outer.StaticInner.staticInnerMethod();      //调用内部类的静态方法
    }
}
