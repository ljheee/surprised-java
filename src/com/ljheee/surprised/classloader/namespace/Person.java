package com.ljheee.surprised.classloader.namespace;

/**
 */
public class Person {
    private String name;
    private Person person;

    public void setPerson(Object o) {
        this.person = (Person) o;
    }
}
