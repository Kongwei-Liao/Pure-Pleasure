import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo implements Runnable {

    Lock lock = new ReentrantLock();

    static int i = 0;

//    public void increase() {
//        i++;
//    }

    public void increase() {
        lock.lock();
        i++;
        System.out.println(Thread.currentThread());
        lock.unlock();
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockDemo test = new LockDemo();

        Thread thread1 = new Thread(test);
        Thread thread2 = new Thread(test);

        thread1.setPriority(4);

        thread1.start();
        thread2.start();

        // 理解一下join，问什么将下面两行注释掉后，打印的结果是 0
        thread1.join();
        thread2.join();

        System.out.println(i);
        System.out.println(Thread.currentThread());
    }
}

/**
 * 为什么没有进行同步的时候，i的值明显小于20000？？
 * 因为两个线程都仅仅维护了自己的副本，没有进行同步
 */
