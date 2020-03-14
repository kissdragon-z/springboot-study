package com.example.springbootstudy.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.springbootstudy.annoation.RateLimter;
import com.example.springbootstudy.bean.OrderDTO;
import com.example.springbootstudy.factory.SelfBeanFactory;
import com.example.springbootstudy.self.SelfBaseService;
import com.example.springbootstudy.service.CheckService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private CheckService checkService;

    @Resource
    private SelfBeanFactory selfBeanFactory;

    //application.properties

//    @Resource
//    private TestBean testBean;

    @GetMapping("/test")
    @RateLimter(value = "test", maxCount = 5)
    public String test() {

        return "test";

    }

    @GetMapping("/innerTest")
    public String innerTest() {

        checkService.check();

        return "OK";

    }

    @GetMapping("/orderInfo")
    public OrderDTO orderInfo(@RequestParam("orderNo") String orderNo) {

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderNo(orderNo);
        orderDTO.setPayStatus(0);
        orderDTO.setDesc("交易失败");
        orderDTO.setAmount(100L);
        return orderDTO;

    }

    @GetMapping("/checkTest")
    public String checkTest(@RequestParam("className") String className) {

        selfBeanFactory.registry(className);

        SelfBaseService selfBaseService = selfBeanFactory.getBean(className);

        JSONObject reqObj = new JSONObject();

        reqObj.put("orderNo", "111111222333");
        reqObj.put("payStatus", 1);
        reqObj.put("amout", "101");

        selfBaseService.execute(reqObj.toJSONString());

        return "OK";

    }

}
