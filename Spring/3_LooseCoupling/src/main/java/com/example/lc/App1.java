package com.example.lc;

import com.example.lc.out.IOutputGenerator;
import com.example.lc.out.impl.CsvOutputGenerator;

public class App1 {
    public static void main(String[] args) {
        IOutputGenerator output = new CsvOutputGenerator();
        output.generateOutput();
    }
}
