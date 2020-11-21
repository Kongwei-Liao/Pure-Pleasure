package com.example.bd.anno.entity;

import org.springframework.stereotype.Component;

@Component        //相当于@Component("user")
public class User {
    public User() {
        System.out.println("User被实例化 = " + this);
    }
}