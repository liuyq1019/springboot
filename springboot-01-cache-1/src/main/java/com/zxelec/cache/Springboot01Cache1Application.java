package com.zxelec.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
@EnableCaching
@MapperScan(value = "com.zxelec.cache.mapper")
//@EnableAutoConfiguration
@SpringBootApplication
public class Springboot01Cache1Application {

	public static void main(String[] args) {
		SpringApplication.run(Springboot01Cache1Application.class, args);
	}

}
