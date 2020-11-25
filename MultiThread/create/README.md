实现接口VS继承Thread
Java 不支持多重继承，继承了Thread 类就无法继承其它类，但可以实现多个接口

方法名称 描述 
public static Thread currentThread() 返回目前正在执行的线程
public final String getName() 返回线程的名称
public final int getPriority() 返回线程的优先级
public final void setPriority(int newPriority) 设置线程优先级
public final boolean isAlive() 判断线程是否在活动，如果是，返回true，否则返回false
public final void join() 调用该方法的线程强制执行，其它线程处于阻塞状态，该线程执行完毕后，其它线程再执行
public static void sleep(long millis) 使当前正在执行的线程休眠millis毫秒，线程处于阻塞状态
public static void yield() 当前正在执行的线程暂停一次，允许其他线程执行，不阻塞，线程进入就绪状态，如果没有其他等待执行的线程，这个时候当前线程就会马上恢复执行
public final void stop() 强迫线程停止执行，已过时，不推荐使用

线程通信
