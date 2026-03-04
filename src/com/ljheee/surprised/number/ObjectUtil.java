package com.ljheee.surprised.number;

import java.util.Objects;

/**
 * @author lijianhua.
 */
public class ObjectUtil {


    public static void main(String[] args) {
        String dbValue = "1";
        Integer domainValue = 1;

        // false
        boolean equals = Objects.equals(dbValue, domainValue);
        System.out.println(equals);
    }
}
