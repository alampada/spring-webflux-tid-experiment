package com.example.demo;

import java.util.Map;

import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import reactor.core.scheduler.Schedulers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class DemoApplication {

	public static void main(String[] args) {


		SpringApplication.run(DemoApplication.class, args);
	}

}
