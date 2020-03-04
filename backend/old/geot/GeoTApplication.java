package old.geot;


import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages = {
        "com.ruyo.rest.geot.dao",
        "com.ruyo.rest.geot.service",
        "com.ruyo.rest.geot.config",
        "com.ruyo.rest.geot.controller"})
@EntityScan(basePackages = {"com.ruyo.rest.geot.entity"})
@SpringBootApplication(exclude = JpaRepositoriesAutoConfiguration.class)
@EnableTransactionManagement
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
