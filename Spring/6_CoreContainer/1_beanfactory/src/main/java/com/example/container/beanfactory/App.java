package com.example.container.beanfactory;

import com.example.container.beanfactory.entity.HelloWorld;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class App {
    public static void main(String[] args) {
        XmlBeanFactory factory = new XmlBeanFactory
                (new ClassPathResource("ApplicationContext.xml"));
        // BeanFactory 默认懒加载
//        HelloWorld obj = (HelloWorld) factory.getBean("helloWorld");
//        obj.getMessage();
    }
}