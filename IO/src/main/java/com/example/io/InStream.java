package com.example.io;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InStream {
    public static void main(String[] args) throws IOException {
        // 创建一个FileInputStream对象:
        InputStream input = new FileInputStream("IO/src/readme.txt");
        for (; ; ) {
            int n = input.read(); // 反复调用read()方法，直到返回-1
            if (n == -1) {
                break;
            }
            System.out.println(n);// 打印byte的值
        }

        input.close(); // 关闭流


        // 直接读取大文件  与  缓冲读取文件对比
        long start = System.currentTimeMillis();
        InputStream inputStream = new FileInputStream("/Users/liaogangwei/Downloads/Wireshark-win64-2.4.3.0.exe");
        for (; ; ) {
            int n = inputStream.read(); // 反复调用read()方法，直到返回-1
            if (n == -1) {
                break;
            }
        }
        inputStream.close();
        long end = System.currentTimeMillis();
        System.out.println("直接读取大文件耗时：" + (end - start));   // 46269ms

        // 缓冲读取文件
        start = System.currentTimeMillis();
        try (InputStream input2 = new FileInputStream("/Users/liaogangwei/Downloads/Wireshark-win64-2.4.3.0.exe")) {
            // 定义1000个字节大小的缓冲区:
            byte[] buffer = new byte[10000];
            int n;
            while ((n = input2.read(buffer)) != -1) { // 读取到缓冲区
                //System.out.println("read " + n + " bytes.");
            }
        }
        end = System.currentTimeMillis();
        System.out.println("缓冲读取大文件耗时：" + (end - start));   // 56ms


        byte[] data = { 72, 101, 108, 108, 111, 33 };
        try (InputStream input3 = new ByteArrayInputStream(data)) {
            int n;
            while ((n = input3.read()) != -1) {
                System.out.print((char)n);
            }
            System.out.println();
        }
    }
}
