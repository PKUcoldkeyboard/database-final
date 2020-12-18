package com.cuterwrite.dbfinal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.cuterwrite.dbfinal.dao")
@EnableAsync
@SpringBootApplication
public class DbFinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbFinalApplication.class, args);
	}

}
