package com.project.nikiha.loser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
public class LoserApplication {
	@Bean
	public RestTemplate restTemplate() {
        return new RestTemplate();
    }

	public static void main(String[] args) {
		SpringApplication.run(LoserApplication.class, args);
	}

}
