package com.example.lc.helper;

import com.example.lc.out.IOutputGenerator;
import com.example.lc.out.impl.CsvOutputGenerator;

public class OutputHelper {
    IOutputGenerator outputGenerator;

    public OutputHelper() {
        outputGenerator = new CsvOutputGenerator(); // 改动outputGenerator = new JsonOutputGenerator();
    }

    public void generateOutput() {
        outputGenerator.generateOutput();
    }

    public void setOutputGenerator(IOutputGenerator outputGenerator){
        this.outputGenerator = outputGenerator;
    }
}