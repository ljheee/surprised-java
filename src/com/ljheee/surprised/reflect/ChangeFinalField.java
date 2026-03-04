package com.ljheee.surprised.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author lijianhua.
 */
public class ChangeFinalField {

    private final Integer age = 10;
    private final int finalPrimitive = 10;


    private static final Integer staticFinal = 10;
    private static final int staticFinalPrimitive = 10;


    public static void main(String[] args) throws Exception {

        ChangeFinalField changeFinalField = new ChangeFinalField();


        changeFinalBoxedType(changeFinalField);
        System.out.println(changeFinalField.age);

        changeStaticFinalBoxedType();
        System.out.println(ChangeFinalField.staticFinal);


        changeFinalPrimitive(changeFinalField);
        System.out.println(changeFinalField.finalPrimitive);

        changeStaticFinalPrimitive();
        System.out.println(ChangeFinalField.staticFinalPrimitive);


    }


    /**
     * static final的包装类型，需要重置Modifier
     *
     * @throws Exception
     */
    private static void changeStaticFinalBoxedType() throws Exception {

        Class<ChangeFinalField> clazz = ChangeFinalField.class;

        Class<Field> fieldClass = Field.class;
        Field fieldModifiers = fieldClass.getDeclaredField("modifiers");
        fieldModifiers.setAccessible(true);

        Field field = clazz.getDeclaredField("staticFinal");
        field.setAccessible(true);


        // 不重置Modifier，会报错IllegalAccessException: Can not set static final
        fieldModifiers.set(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, 20);
    }


    /**
     * final 的包装类型，普通反射即可修改
     *
     * @param changeFinalField
     * @throws Exception
     */
    private static void changeFinalBoxedType(ChangeFinalField changeFinalField) throws Exception {
        Class<ChangeFinalField> clazz = ChangeFinalField.class;

        Field field = clazz.getDeclaredField("age");
        field.setAccessible(true);
        field.set(changeFinalField, 20);
    }


    /**
     * final的基本类型，无法用反射修改；
     * 即使重置Modifier
     *
     * @param changeFinalField
     * @throws Exception
     */
    private static void changeFinalPrimitive(ChangeFinalField changeFinalField) throws Exception {
        Class<ChangeFinalField> clazz = ChangeFinalField.class;

        Class<Field> fieldClass = Field.class;
        Field fieldModifiers = fieldClass.getDeclaredField("modifiers");
        fieldModifiers.setAccessible(true);


        Field field = clazz.getDeclaredField("finalPrimitive");
        field.setAccessible(true);

        fieldModifiers.set(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(changeFinalField, 20);
    }

    private static void changeStaticFinalPrimitive() throws Exception {
        Class<ChangeFinalField> clazz = ChangeFinalField.class;

        Class<Field> fieldClass = Field.class;
        Field fieldModifiers = fieldClass.getDeclaredField("modifiers");
        fieldModifiers.setAccessible(true);


        Field field = clazz.getDeclaredField("staticFinalPrimitive");
        field.setAccessible(true);

        fieldModifiers.set(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, 20);
    }


}
