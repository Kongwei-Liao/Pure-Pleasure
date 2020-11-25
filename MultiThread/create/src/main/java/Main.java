import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {

    public static void main(String[] args) {

        /**
         * 第一种方式创建线程：继承Tread类重写run方法
         *      第一步：创建一个Thread类的子类
         *      第二步：子类重写run方法，run方法内设置线程任务
         *      第三步：创建子类对象
         *      第四步：调用线程对象的start方法
         *  start使该线程开始执行；Java虚拟机内执行run方法内的线程任务
         *  当前线程（main线程）和另一个线程（创建并start的线程）
         *  多次启动一个线程是非法的，特别是当线程已经结束执行后，不能在重新启动。
         *
         * java程序属于抢占式调度，哪个线程的优先级高哪个线程优先执行，同一个优先级的线程随机选择执行
         */
        DemoThread demoThread = new DemoThread();
        demoThread.start();

//        DemoThread demoThread1 = new DemoThread();
//        demoThread1.start();

        for (int i = 0; i < 20; i++) {
            System.out.println("main:" + i);
        }

        /**
         * 第二种方式：实现Runnable接口的方式
         *      第一步：创建一个线程类实现Runnable接口，重写接口中的run方法
         *      第二步：创建第一步线程类的对象
         *      第三步：调用对象的start方法启动线程。
         */
//        DemoThread1 demoThread1 = new DemoThread1();
//        demoThread1.start();  // 失败：Runnable接口仅仅是一个函数式接口，方便使用lambda表达式，并没有start方法
        new Thread(new DemoThread1()).start();

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println("new Thread:" + i);
                }
            }
        }.start();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println("new Runnable:" + i);
                }
            }
        };

        new Thread(runnable).start();


        /**
         * 第三种：实现Callable接口
         *
         * 与 Runnable 相比，Callable 可以有返回值，返回值通过 FutureTask 进行封装。
         */

        DemoThread3 demoThread3 = new DemoThread3();
        FutureTask<Integer> futureTask = new FutureTask<>(demoThread3);
        Thread thread=new Thread(futureTask);
        thread.start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        /**
         * 注意事项：
         * Thread.run()   仅仅是调用对象的方法，线程并没有运行
         * Thread.start()  启动线程并执行该线程的run方法
         */
    }
}
