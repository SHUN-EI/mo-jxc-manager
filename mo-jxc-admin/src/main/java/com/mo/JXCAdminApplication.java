package com.mo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by mo on 2022/10/31
 */
@MapperScan("com.mo.mapper")
@SpringBootApplication
public class JXCAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(JXCAdminApplication.class, args);
    }

}
