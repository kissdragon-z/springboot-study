package com.example.springbootstudy.annoation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RateLimter {

    /**
     * 资源名
     *
     * @return
     */
    String value() default "";

    /**
     * 次数
     *
     * @return
     */
    int maxCount() default 1;

}
