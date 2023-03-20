package com.madou.geapiinterface;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.madou.geapiinterface.mapper")
public class GeapiInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeapiInterfaceApplication.class, args);
    }

}
