package com.example.bd.javaconfig.component;

import org.springframework.stereotype.Component;

@Component
public class Customer {
    public Customer() {
        System.out.println("Customer被实例化 = " + this);
    }
}