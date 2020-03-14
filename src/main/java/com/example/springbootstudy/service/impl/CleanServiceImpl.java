package com.example.springbootstudy.service.impl;

import com.example.springbootstudy.service.CleanService;
import org.springframework.stereotype.Service;

@Service
public class CleanServiceImpl implements CleanService {
    @Override
    public void clean() {

        System.out.println("this is clean service");

    }
}
