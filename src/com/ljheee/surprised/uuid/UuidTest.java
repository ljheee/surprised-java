package com.ljheee.surprised.uuid;

import java.util.Random;
import java.util.UUID;

/**
 * UUID 多线程下重复问题讨论 https://www.oschina.net/question/614853_2150838
 * <p>
 * http://www.dcalabresi.com/blog/java/generate-java-uuid-performance/
 * https://github.com/cowtowncoder/java-uuid-generator
 */
public class UuidTest {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(generateSequenceID());
                }
            }).start();
        }
    }

    public static String generateSequenceID() {
        String uuid = UUID.randomUUID().toString();
        String ranEight = String.format("%08d", new Random().nextInt(99999999));
        return uuid + "--" + ranEight;
    }


}
