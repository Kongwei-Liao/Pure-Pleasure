package com.example.container.applicationcontext;

import com.example.container.applicationcontext.entity.HelloWorld;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class App {
    public static void main(String[] args) {

        // 路径拷贝 Path From Repository Root
        ApplicationContext context = new FileSystemXmlApplicationContext
                ("Spring/6_CoreContainer/2_applicationcontext/src/main/resources/ApplicationContext.xml");

        HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
        obj.getMessage();
    }
}
