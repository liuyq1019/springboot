package com.zxelec.cache;

//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
//@MapperScan(value = "com.zxelec.cache.mapper")
public class Springboot01Cache1Application {

	public static void main(String[] args) {
		SpringApplication.run(Springboot01Cache1Application.class, args);
	}

}
