package za.co.momentum.tabletennis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@ComponentScan(basePackages = { "za.co.momentum.tabletennis" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/rest/*").allowedOrigins("http://localhost:4200", "http://192.168.0.4:4200",
						"http://192.168.0.5:4200", "http://105.184.227.10:81", "*");
				registry.addMapping("/rest/auth/*").allowedOrigins("http://localhost:4200", "http://192.168.0.4:4200",
						"http://105.184.227.10:81", "*");
			}
		};
	}
}
