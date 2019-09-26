##### demo程序如何 让程序gc
1、调整jvm 的堆大小
2、不断创建对象，占用年轻代；

我想知道当前gc执行了多少次
jstat -gc
YGC标识的数字代表young gc次数。


弱引用：gc执行时就回收。

##### ThreadLocal 为什么会造成内存泄露
key是弱引用，被回收了，而value还存在，常驻内存。		key是什么————key是threadLocal
线程生命周期没结束，value一直在内存。

key为null了，我们不能获取到value，但value却一直在内存。



##### threadlocal应用
保存jdbc
spring事务传播行为：当前有事务 就加入当前事务；如果当前没有事务 就新建。
RequestContextHolder 保存request；



可以点进去之后方法 应该在finally里面有remove掉
用ThreadLocal时，不清楚什么时候会执行完。


##### ThreadLocal有什么缺陷？
1、父子线程传值丢失，需要使用可继承的ThreadLocal
2、使用线程池时，InheritableThreadLocal 在父线程更新的值不能传递给子线程(子线程获取的还是旧值)

线程池，使用复用的线程时，获取的绑定变量是旧值。
线程复用，也就是线程一直不断使用，一直没释放。

