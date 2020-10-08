package com.purepleasure.javaagent;

import java.lang.instrument.Instrumentation;

public class MyAgent {
    public static void premain(String[] agentArgs, Instrumentation inst) {
        System.out.println("Premain started!");
        System.out.println(agentArgs);
    }
}
