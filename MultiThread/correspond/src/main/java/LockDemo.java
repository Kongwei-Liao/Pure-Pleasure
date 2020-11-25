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

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println(i);
    }
}
