package com.example.demo;

import java.util.Map;

import lombok.extern.log4j.Log4j2;
import org.slf4j.MDC;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

@Component
@Log4j2
public class TidFIlter implements WebFilter {

	static {
		Schedulers.onScheduleHook("mdc", runnable -> {
			Map<String, String> map = MDC.getCopyOfContextMap();
			return () -> {
				if (map != null) {
					MDC.setContextMap(map);
				}
				try {
					runnable.run();
				}
				finally {
					MDC.clear();
				}
			};
		});
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		var tid = exchange.getRequest().getHeaders().getFirst("tid");
		log.info("in filter, tid " + tid);
		MDC.put("TID", tid);
		return chain.filter(exchange);
	}
}
