package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.example.demo.mapper")
@EnableTransactionManagement
public class OnlineClassApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineClassApplication.class, args);
	}

}
