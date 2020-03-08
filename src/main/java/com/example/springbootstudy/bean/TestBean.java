package com.example.springbootstudy.bean;

public class TestBean {

    private String name;

    private Long id;

    public TestBean() {

    }

    public TestBean(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public void testbean() {
        System.out.println("name=" + name + ",id=" + id);
    }
}
