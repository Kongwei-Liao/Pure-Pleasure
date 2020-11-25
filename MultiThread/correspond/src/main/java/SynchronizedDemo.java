public class SynchronizedDemo implements Runnable {
    static int i = 0;

    public void increase() {
        i++;
    }

// 同步方法
//    public synchronized void increase() {
//        i++;
//    }

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            increase();
        }
// 同步代码块
//        synchronized (this){
//            for (int j = 0; j < 10000; j++) {
//                increase();
//            }
//        }

    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedDemo test = new SynchronizedDemo();

        Thread thread1 = new Thread(test);
        Thread thread2 = new Thread(test);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println(i);
    }
}
