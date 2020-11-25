/**
 * 错误方法
 */
public class DemoThread1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("demoThread1" + i);
        }
    }
}
