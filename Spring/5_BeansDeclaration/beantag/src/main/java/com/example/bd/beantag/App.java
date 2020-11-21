package com.example.bd.beantag;

import com.example.bd.beantag.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");

//        User user = (User)context.getBean("user");
//        System.out.println(user);
    }
}