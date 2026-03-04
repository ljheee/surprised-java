# 对象

## 1：对象的创建过程

    申请内存
    成员变量赋默认值
    init 构造方法
        成员变量顺序调用
        初始化成员变量
        构造方法调用

## 2：对象的内存布局

    对象头 markword
    类型指针
    对象的成员变量
    补齐

## 3：对象头里具体包括什么

    hashcode
    锁的信息（2位 四种组合）
    GC信息（年龄）
    如果是数组，数组的长度

## 4：类型指针指向什么地方

    new Cat()
    pointer -> Cat.class
    寻找方法的信息

## 5：对象定位

    1：句柄池 （指针池）间接指针，节省内存
    2：直接指针，访问速度快

## 6：对象的分配过程

    栈上分配
    TLAB（Thread Local Allocation Buffer）
    Old
    Eden
    老不死 - > Old

## 7：Object o = new Object()占多少字节（hotspot）

    new Object()
    markword          8个字节
    类型指针           8个字节
    实例变量           0
    补齐                  0		
    16字节（压缩 非压缩）
    Object o
    8个字节 
    JVM参数指定压缩或非压缩

