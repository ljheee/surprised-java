package com.ljheee.surprised.hotspot.vtable;

/**
 * @author lijianhua.
 */
public class Vtable {


    class A {
        @Override
        public String toString() {
            return "classA";
        }
    }
    class B extends A{

        public void func(){
            System.out.println("func in classB");
        }
        @Override
        public String toString() {
            return "classB";
        }
    }

}
