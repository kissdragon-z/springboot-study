package com.example.springbootstudy.config;

import com.example.springbootstudy.bean.TestBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudyConfig {

    @Value("${test.name:12321}")
    private String name;

    @Value("${test.id:12}")
    private Long id;

    @Bean
    TestBean createTestBean() {
        TestBean testBean = new TestBean(name, id);

        return testBean;
    }
}
