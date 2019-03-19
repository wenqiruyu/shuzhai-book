package com.shuzhai.book;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shuzhai.book.dao")
public class ShuzhaiBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShuzhaiBookApplication.class, args);
    }

}

