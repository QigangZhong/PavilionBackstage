package com.pavilion;

import org.apache.log4j.PropertyConfigurator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.pavilion.dao")
@ServletComponentScan
public class Application {
    static {

    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
