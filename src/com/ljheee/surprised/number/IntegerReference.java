package com.ljheee.surprised.number;

import java.util.Objects;

/**
 * Java中只有值传递
 * 引用/对象类型，方法传参是浅拷贝；
 *
 * Integer作为参数，方法内部做+1操作，方法外部无感知。
 * Integer 类中final的value字段，说明一旦integer类创建之后他的值就不能被修改，在 index++ 的时候Integer是创建一个新的类，所以这个第二次输出的时候结果是一样的！
 */
public class IntegerReference {


    public static void main(String[] args) {
        Integer index = 0;


        System.out.println(System.identityHashCode(index) + "," + index);
        plusOne(index);
        System.out.println(System.identityHashCode(index) + "," + index);

    }


    public static void plusOne(Integer index) {
        index++;
        System.out.println(System.identityHashCode(index) + "," + index);
    }
}
