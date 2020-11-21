package com.example.start.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("XMLContext.xml");
        HelloWorld helloBean = (HelloWorld) context.getBean("helloBean");
        helloBean.printHello();
    }
}