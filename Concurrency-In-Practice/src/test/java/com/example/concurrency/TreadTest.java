package com.example.concurrency;

public class TreadTest {
    public static final int DELAY = 10;
    public static final int STEPS = 100;
    public static final double MAX_AMOUNT = 1000;

    public static void main(String[] args) {

        Bank bank = new Bank(4, 100000);

        Runnable task1 = () -> {
            try {
                for (int i = 0; i < STEPS; i++) {
                    System.out.println("task1");
                    double amount = MAX_AMOUNT * Math.random();
                    bank.transfer(0, 1, amount);
                    Thread.sleep((int) (DELAY * Math.random()));
                }
            } catch (InterruptedException e) {

            }
        };

        Runnable task2 = () -> {
            try {
                for (int i = 0; i < STEPS; i++) {
                    System.out.println("task2");
                    double amount = MAX_AMOUNT * Math.random();
                    bank.transfer(2, 3, amount);
                    Thread.sleep((int) (DELAY * Math.random()));
                }
            } catch (InterruptedException e) {

            }
        };

        new Thread(task1).start();
        new Thread(task2).start();

        // 这里亟待完善start和run的区别

    }
}
