package com.cuterwrite.dbfinal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.cuterwrite.dbfinal.dao")
@SpringBootApplication
public class DbFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbFinalApplication.class, args);
	}

}
