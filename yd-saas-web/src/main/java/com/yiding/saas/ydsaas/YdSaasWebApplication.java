package com.yiding.saas.ydsaas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * 启动器
 *
 * @author Louis
 * @date Oct 29, 2018
 */
@SpringBootApplication
@MapperScan("com.yiding.saas.ydsaas.dao.*")
public class YdSaasWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(YdSaasWebApplication.class, args);
    }
}