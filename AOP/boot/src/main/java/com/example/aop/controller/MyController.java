package com.example.aop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @RequestMapping("/hello")
    public Object hello() {
        return "hello";
    }

    @RequestMapping("/doError")
    public Object error() {
        return 1 / 0;
    }
}