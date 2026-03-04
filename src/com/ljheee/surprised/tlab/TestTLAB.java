package com.ljheee.surprised.tlab;

/**
 * 线程本地分配 Thread Local Allocation Buffer
 * -XX:-DoEscapeAnalysis -XX:-EliminateAllocations -XX:-UseTLAB	-verbose:gc
 */
public class TestTLAB {

    class UserInfo {
        int id;
        String name;

        public UserInfo(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public void alloc(int i) {
        new UserInfo(i, "name" + i);
    }


    public static void main(String[] args) {
        TestTLAB t = new TestTLAB();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000_0000; i++) {
            t.alloc(i);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}
