package com.jenson.studying;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.jenson.studying")
@MapperScan({"com.jenson.studying.system.mapper"})
public class StudyingApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyingApplication.class, args);
    }
}
