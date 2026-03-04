package com.ljheee.surprised.number;

import java.util.Arrays;
import java.util.List;

/**
 * 基本类型不支持泛型化
 * 基本类型数组，不能直接转成包装类型的数组
 */
public class Array2List {

    public static void main(String[] args) {

        long arr[] = new long[]{1, 4, 3, 3,};

        String sts[] = new String[]{"a", "b", "c", "c"};

        /**
         * 基本类型，转成的是数组类型；
         * 原因是：基本类型数组，不能直接转成包装类型的数组
         */
        List<long[]> longs = Arrays.asList(arr);
        System.out.println(longs.size());// 1

        /**
         * 包装类型，转成的直接就是列表
         */
        List<String> stringList = Arrays.asList(sts);
        System.out.println(stringList.size());// 4

    }
}
