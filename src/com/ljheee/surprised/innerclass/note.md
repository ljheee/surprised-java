

静态内部类加载时机
静态内部类只有当被调用的时候才开始首次被加载。

这样的话，Finalizer被使用，也不会加载FinalizerThread。

java.lang.ref.Finalizer.FinalizerThread
作为静态内部类，在static代码块中start()线程，这个static代码块何时被执行的呢？


FinalizerThread是属于system thread group系统线程组。
https://www.iflym.com/index.php/code/201609050001.html