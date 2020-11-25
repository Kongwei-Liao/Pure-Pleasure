wait-notify

public final native void wait(long timeoutMillis) throws InterruptedException;
public final native void notify();
public final native void notifyAll();

wait: 使当前线程等待，直到另一个线程调用此对象的notify()方法或notifyAll()方法
notify/notifyAll: 唤醒一个或多个处于等待状态的线程

join:在一个线程中调用other.join()，等待另一个线程执行结束后才继续执行本线程。
yield:线程让出CPU使用权，从运行态进入就绪态，CPU会从就绪态的线程中重新选择一个线程执行，也就是说，刚刚那个线程还是有可能被再次执行。


