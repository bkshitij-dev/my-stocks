package com.thedevjournal.mystocks;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.thedevjournal.mystocks.mapper")
public class MyStocksApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyStocksApplication.class, args);
	}

}
