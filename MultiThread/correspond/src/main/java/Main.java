

public class Main {
    public static void main(String[] args) {

        /**
         * 思考题：有2个字符串，String a = "1234567"; String b = "abcdefg"，编写两个线程，对这两个字符串中的字符交替输出，使得输出结果为1a2b3c4d5e6f7g
         */
        Object o = new Object();
        String a = "1234567";
        String b = "abcdefg";

        new Thread(() -> {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            char[] chars = a.toCharArray();
            synchronized (o) {
                for (char c : chars) {
                    System.out.print(c);
                    // 唤醒一个处于等待的线程
                    o.notify();
                    try {
                        // 使该线程等待，直到被另一个线程唤醒
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 唤醒一个处于等待的线程
                o.notify();
            }
        }, "t1").start();

        new Thread(() -> {
            char[] chars = b.toCharArray();
            synchronized (o) {
                for (char c : chars) {
                    System.out.print(c);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }, "t2").start();


        /**
         *
         */
    }
}
