package com.dingel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.dingel.server.mapper")
@EnableScheduling
public class LightApplication {
    public static void main(String[] args) {
        SpringApplication.run(LightApplication.class,args);
    }

}
