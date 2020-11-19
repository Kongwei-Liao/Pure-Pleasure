package com.example.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InStream {
    public static void main(String[] args) throws IOException {
        // 创建一个FileInputStream对象:
        InputStream input = new FileInputStream("src/readme.txt");
        for (; ; ) {
            int n = input.read(); // 反复调用read()方法，直到返回-1
            if (n == -1) {
                break;
            }
            System.out.println(n); // 打印byte的值
        }
        input.close(); // 关闭流
    }
}
