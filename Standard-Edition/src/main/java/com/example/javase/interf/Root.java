package com.example.javase.interf;

public interface Root {
    default void print(){
        System.out.println("hello world");
    }
}
