package com.nyuen.dxtest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DxtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DxtestApplication.class, args);
    }

}
