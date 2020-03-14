package com.example.springbootstudy.service.impl;

import com.example.springbootstudy.service.CheckService;
import com.example.springbootstudy.service.CleanService;
import org.springframework.stereotype.Service;

@Service
public class CheckServiceImpl implements CheckService {


    private CleanService cleanService;

    CheckServiceImpl(CleanService cleanService){

        this.cleanService = cleanService;

    }




    @Override
    public void check() {

        cleanService.clean();

        System.out.println("this is check service");
    }
}
