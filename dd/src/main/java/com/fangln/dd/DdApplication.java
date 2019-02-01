package com.fangln.dd;

import com.fangln.dd.util.CoreProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@ServletComponentScan
@SpringBootApplication(scanBasePackages = {"com.fangln.dd"})
@EnableConfigurationProperties({CoreProperties.class})
@MapperScan("com.fangln.dd.dao")

public class DdApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdApplication.class, args);
    }
}
