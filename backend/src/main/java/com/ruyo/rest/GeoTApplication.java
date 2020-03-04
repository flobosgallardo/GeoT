package com.ruyo.rest;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
        "com.ruyo.rest.config",
		"com.ruyo.rest.dao",
		"com.ruyo.rest.dao.factory",
        "com.ruyo.rest.dao.repository",
        "com.ruyo.rest.entity",
        "com.ruyo.rest.restcontroller"})
@EntityScan(basePackages = {"com.ruyo.entity"})
@SpringBootApplication(exclude = JpaRepositoriesAutoConfiguration.class)
public class GeoTApplication extends SpringBootServletInitializer {


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return configureApplication(builder);
	}

	public static void main(String[] args) {
		SpringApplication.run(GeoTApplication.class, args);
	}

	private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
		return builder.sources(GeoTApplication.class).bannerMode(Banner.Mode.OFF);
	}
}
