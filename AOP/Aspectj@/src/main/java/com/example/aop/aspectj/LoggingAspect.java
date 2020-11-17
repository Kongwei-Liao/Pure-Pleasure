package com.example.aop.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect
public class LoggingAspect {

    @Before("execution(* com.example.aop.aspectj.CustomerBo.addCustomer(..))")
    public void logBefore(JoinPoint joinPoint) {

        System.out.println("logBefore() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("******");
    }

    @After("execution(* com.example.aop.aspectj.CustomerBo.addCustomer(..))")
    public void logAfter(JoinPoint joinPoint) {

        System.out.println("logAfter() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("******");

    }

    @AfterReturning(pointcut = "execution(* com.example.aop.aspectj.CustomerBo.addCustomerReturnValue(..))",returning= "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {

        System.out.println("logAfterReturning() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("Method returned value is : " + result);
        System.out.println("******");
    }

    @AfterThrowing(pointcut = "execution(* com.example.aop.aspectj.CustomerBo.addCustomerThrowException(..))",throwing= "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {

        System.out.println("logAfterThrowing() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("Exception : " + error);
        System.out.println("******");

    }

    @Around("execution(* com.example.aop.aspectj.CustomerBo.addCustomerAround(..))")
    public void logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("logAround() is running!");
        System.out.println("hijacked method : " + proceedingJoinPoint.getSignature().getName());
        System.out.println("hijacked arguments : " + Arrays.toString(proceedingJoinPoint.getArgs()));

        System.out.println("Around before is running!");
        proceedingJoinPoint.proceed(); //continue on the intercepted method
        System.out.println("Around after is running!");

        System.out.println("******");

    }

}