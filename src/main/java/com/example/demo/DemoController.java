package com.example.demo;

import java.time.Duration;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

@RestController
@Slf4j
public class DemoController {

	@GetMapping("/")
	public Mono<String> hello(ServerWebExchange exchange) {

		log.info(exchange.getLogPrefix() + " hello ");
		return Mono.just("hello").delayElement(Duration.ofMillis(1000))
				.doOnNext(l -> log.info(exchange.getLogPrefix() + "delayed message"));
	}

}
