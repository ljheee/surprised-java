package com.ljheee.surprised.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author lijianhua.
 */
public class ChangeFieldHasNoSetter {

    private Integer age = 10;

    public static void main(String[] args) throws Exception {

        ChangeFieldHasNoSetter changeFieldHasNoSetter = new ChangeFieldHasNoSetter();

        Class<ChangeFieldHasNoSetter> clazz = ChangeFieldHasNoSetter.class;

        Field field = clazz.getDeclaredField("age");
        field.setAccessible(true);
        field.set(changeFieldHasNoSetter, 20);


        System.out.println(changeFieldHasNoSetter.age);

    }

}
