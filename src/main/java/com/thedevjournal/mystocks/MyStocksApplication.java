package com.thedevjournal.mystocks;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.thedevjournal.mystocks.mapper")
@EnableScheduling
public class MyStocksApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyStocksApplication.class, args);
	}

}
