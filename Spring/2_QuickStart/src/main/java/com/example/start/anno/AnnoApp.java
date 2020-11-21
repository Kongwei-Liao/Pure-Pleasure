package com.example.start.anno;

import com.example.start.anno.HelloWorld;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnoApp {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("AnnoContext.xml");
        HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
        obj.printHello();
    }
}