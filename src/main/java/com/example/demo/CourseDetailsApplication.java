package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.example.demo.backbase"}) 
public class CourseDetailsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseDetailsApplication.class, args);
    }

}
