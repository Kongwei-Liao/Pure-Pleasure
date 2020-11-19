package com.example.io;

import java.io.*;

public class FileAndDir {
    public static void main(String[] args) throws IOException {
        File f = new File("/Users/liaogangwei/Downloads/File");
        // File f = new File("..");
        System.out.println(f);
        System.out.println(f.getPath());            // 构造方法传入的路径
        System.out.println(f.getAbsolutePath());    // 绝对路径
        System.out.println(f.getCanonicalPath());   // 绝对路径类似，但是返回的是规范路径

        System.out.println(f.isFile());
        System.out.println(f.isDirectory());

        System.out.println(f.canRead());
        System.out.println(f.canWrite());
        System.out.println(f.canExecute());

        System.out.println(f.length());

        // f.createNewFile();
        f.mkdir();

        System.out.println(f);
        System.out.println(f.getPath());            // 构造方法传入的路径
        System.out.println(f.getAbsolutePath());    // 绝对路径
        System.out.println(f.getCanonicalPath());   // 绝对路径类似，但是返回的是规范路径

        System.out.println(f.isFile());
        System.out.println(f.isDirectory());

        System.out.println(f.canRead());
        System.out.println(f.canWrite());
        System.out.println(f.canExecute());

        System.out.println(f.length());

        f.delete();

        File f2 = new File("/Users/liaogangwei/Downloads");
        File[] fs1 = f2.listFiles(); // 列出当前目录下所有文件和和一级子目录

        printFiles(fs1);

        File[] fs2 = f2.listFiles(new FilenameFilter() { // 仅列出.exe文件
            public boolean accept(File dir, String name) {
                return name.endsWith(".exe"); // 返回true表示接受该文件
            }
        });
        printFiles(fs2);

    }

    /**
     * 遍历所有所有文件目录
     * @param files
     */
    static void printFiles(File[] files) {
        if (files != null) {
            for (File f : files) {
                if (f.isFile())
                    System.out.println(f);
                else if (f.isDirectory()) {
                    File[] fs = f.listFiles();
                    printFiles(fs);
                }
            }
        }
    }
}