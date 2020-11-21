package com.example.bd.javaconfig;

import com.example.bd.javaconfig.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new
                AnnotationConfigApplicationContext("com.example.bd.javaconfig.config");

        /*
         * context.getBean(User.class);
         * No qualifying bean of type 'com.example.bd.javaconfig.entity.User' available:
         * expected single matching bean but found 2: createtUser1,createtUser2
         */

        User user1 = (User)context.getBean("createtUser1");
        User user1_1 = (User)context.getBean("createtUser1");

        User user2 = (User)context.getBean("createtUser2");


    }
}
