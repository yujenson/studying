package com.jenson.studying;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jenson.studying.modules.demo.mapper")
public class StudyingApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyingApplication.class, args);
    }
}
