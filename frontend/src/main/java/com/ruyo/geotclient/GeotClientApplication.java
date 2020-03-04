package com.ruyo.geotclient;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class GeotClientApplication extends SpringBootServletInitializer {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return configureApplication(builder);
	}
	public static void main(String[] args) {
		SpringApplication.run(GeotClientApplication.class, args);
	}



	private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
		return builder.sources(GeotClientApplication.class).bannerMode(Banner.Mode.OFF);
	}

}
