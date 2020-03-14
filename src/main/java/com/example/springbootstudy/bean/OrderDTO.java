package com.example.springbootstudy.bean;

import lombok.Data;

@Data
public class OrderDTO {

    private String orderNo;
    private int payStatus;
    private String desc;
    private Long amount;
}
