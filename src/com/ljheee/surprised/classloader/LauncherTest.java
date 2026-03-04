package com.ljheee.surprised.classloader;

import sun.misc.Launcher;

/**
 * @author lijianhua.
 */
public class LauncherTest {

    public static void main(String[] args) {

        // 打印null，所以Launcher是由引导类加载器加载
        System.out.println(Launcher.class.getClassLoader());
    }
}
