package com.example.io;

import java.io.*;

public class OutStream {

    public static void main(String[] args) throws IOException {

        File file = new File("/Users/liaogangwei/Desktop/readme.txt");

        if(!file.exists()){
            file.createNewFile();
        }

        OutputStream output = new FileOutputStream(file);
        output.write(72); // H
        output.write(101); // e
        output.write(108); // l
        output.write(108); // l
        output.write(111); // o
        output.close();

        OutputStream output1 = new FileOutputStream("/Users/liaogangwei/Desktop/readme.txt");
        output1.write("Hello123321".getBytes("UTF-8")); // Hello
        output1.close();


        //file.delete();
    }
}
