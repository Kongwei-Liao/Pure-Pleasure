package com.example.aop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    ApplicationContext applicationContext;

    @RequestMapping("/hello")
    public Object hello() {
        System.out.println(applicationContext.getBeanDefinitionNames().length);
        return "hello";
    }

    @RequestMapping("/doError")
    public Object error() {
        return 1 / 0;
    }
}