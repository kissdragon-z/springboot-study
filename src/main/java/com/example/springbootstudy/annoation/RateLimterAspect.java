package com.example.springbootstudy.annoation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RateLimterAspect {

    @Around("within(com.example.springbootstudy..*) && @annotation(RateLimter)")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Exception {

        Object obj = null;
        try {
            obj = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return obj;

    }

}
