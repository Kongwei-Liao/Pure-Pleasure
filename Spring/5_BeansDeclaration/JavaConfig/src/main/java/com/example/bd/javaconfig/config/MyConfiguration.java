package com.example.bd.javaconfig.config;

import com.example.bd.javaconfig.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("com.example.bd.javaconfig.component")
public class MyConfiguration {
    @Scope(scopeName = "prototype")
    @Bean
    public User createtUser1(){
        User user = new User();
        System.out.println("创建 user1 = " + user);
        return user;
    }

    @Bean
    public User createtUser2(){
        User user = new User();
        System.out.println("创建 user2 = " + user);
        return user;
    }

    @Bean
    public void createtUser3(){
        User user = new User();
        System.out.println("创建 user2 = " + user);
    }
}
