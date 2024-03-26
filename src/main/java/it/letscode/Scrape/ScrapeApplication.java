package it.letscode.Scrape;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ScrapeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScrapeApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ScrapperService scrapperService) {
		return args -> scrapperService.run();
	}
}
