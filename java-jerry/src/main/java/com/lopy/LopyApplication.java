package com.lopy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class LopyApplication {

	public static void main(String[] args) {
		SpringApplication.run(LopyApplication.class, args);
	}
}
