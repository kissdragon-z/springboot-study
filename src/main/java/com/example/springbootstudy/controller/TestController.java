package com.example.springbootstudy.controller;

import com.example.springbootstudy.annoation.RateLimter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    //application.properties

//    @Resource
//    private TestBean testBean;

    @GetMapping("/test")
    @RateLimter(value = "test", maxCount = 5)
    public String test() {

        return "test";

    }

}
