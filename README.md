`surprised java, which you unexpected.`

- FinalStaticTest
类的加载时机 JVM规范规定，类初始化被延迟的情况：引用类的静态常量(static final)不会导致类的初始化。
经过编译优化，ClassA引用ClassB类的静态常量(static final)时，该静态常量已经存到ClassA类自身常量池中，不会加载ClassB


- Integer用作锁
1、如果只是synchronized(Integer)，作为锁对象，不在同步块修改Integer的值，完全没问题，和缓存范围无关，用作锁对象的Integer的值是在缓存范围还是不在，都没影响。
    演示IntegerLock
2、synchronized(Integer)，在同步块修改Integer的值，此时每次锁住的都是不同的对象实例(valueOf()不在缓冲区的都产生新Integer)。
    演示IntegerLock
    
    
threadlocal
- ThreadLocal 为什么会造成内存泄露
- InheritableThreadLocal 在使用线程池时的缺陷