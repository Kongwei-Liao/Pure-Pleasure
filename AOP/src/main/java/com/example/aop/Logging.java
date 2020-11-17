package com.example.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Logging {

    /**
     * Following is the definition for a pointcut to select
     * all the methods available. So advice will be called
     * for all the methods.
     */
    @Pointcut("execution(* com.example.aop.Student.getAge(..))")
    private void selectGetAge() {
    }

    /**
     * This is the method which I would like to execute
     * before a selected method execution.
     */
    @Before("selectGetAge()")
    public void beforeAdvice() {
        System.out.println("[beforeAdvice] Going to get student profile.");
    }

    @Before("@annotation(com.example.aop.Loggable)")
    public void beforeAdvice1() {
        System.out.println("[beforeAdvice1] Going to set student profile.");
    }
}
