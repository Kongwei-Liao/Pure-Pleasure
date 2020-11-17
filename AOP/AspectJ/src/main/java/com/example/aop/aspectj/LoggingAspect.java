package com.example.aop.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class LoggingAspect {

    @Before("execution(* com.example.aop.aspectj.CustomerBo.addCustomer(..))")
    public void logBefore(JoinPoint joinPoint) {
        //...
    }

    @After("execution(* com.example.aop.aspectj.CustomerBo.addCustomer(..))")
    public void logAfter(JoinPoint joinPoint) {
        //...
    }

    @AfterReturning(
            pointcut = "execution(* com.example.aop.aspectj.CustomerBo.addCustomerReturnValue(..))",
            returning= "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        //...
    }

    @AfterThrowing(
            pointcut = "execution(* com.example.aop.aspectj.CustomerBo.addCustomerThrowException(..))",
            throwing= "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        //...
    }

    @Around("execution(* com.example.aop.aspectj.CustomerBo.addCustomerAround(..))")
    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //...
    }


}