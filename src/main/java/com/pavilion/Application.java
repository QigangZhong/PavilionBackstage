package com.pavilion;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.pavilion.dao")
@ServletComponentScan
public class Application {
    static Logger logger = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {

        logger.info("启动应用");
        SpringApplication.run(Application.class,args);
    }
}
