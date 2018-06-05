package com.fangln.dd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fangln.dd.dao")
public class DdApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdApplication.class, args);
    }
}
