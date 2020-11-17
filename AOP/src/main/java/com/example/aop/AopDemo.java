package com.example.aop;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopDemo {
    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("Beans.xml");

        Student student = (Student) context.getBean("student");

        AspectJProxyFactory proxyFactory = new AspectJProxyFactory(student);
        proxyFactory.addAspect(Logging.class);

        Student proxyStudent = proxyFactory.getProxy();
        proxyStudent.getAge();

        Student proxyStudent1 = proxyFactory.getProxy();
        proxyStudent1.setAge(27);
        proxyStudent1.getAge();

    }
}
