package com.example.concurrency;

import java.util.Arrays;

public class Bank {
    private final double[] accounts;

    public Bank(int n, double initialBalabce) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalabce);
    }

    /**
     * 该银行内部账户之间的交易
     * @param from
     * @param to
     * @param amount
     */
    public void transfer(int from, int to, double amount) {
        if (accounts[from] < amount) return;
        System.out.println(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf("%10.2f from %d to %d", amount, from, to);    // print format
        accounts[to] += amount;
        System.out.printf("Total Balance: %10.3f/n", getTotalBalance());
    }

    /**
     * 获取整个银行所有账户的存款
     * @return 整个银行所有账户的存款
     */
    public double getTotalBalance() {
        double sum = 0;

        for (double a : accounts) {
            sum += a;
        }
        return sum;
    }

    /**
     * 获取这个银行的账户数目
     * @return 这个银行的账户数目
     */
    public int size() {
        return accounts.length;
    }
}
