package com.maranhao.turismoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TurismoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TurismoApiApplication.class, args);
	}

}
