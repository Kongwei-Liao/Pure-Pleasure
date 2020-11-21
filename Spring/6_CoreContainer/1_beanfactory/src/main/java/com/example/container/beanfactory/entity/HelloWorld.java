package com.example.container.beanfactory.entity;

public class HelloWorld {
    private String message;

    public HelloWorld() {
        System.out.println("HelloWorld 实例化 " + this);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void getMessage() {
        System.out.println("Your Message : " + message);
    }
}