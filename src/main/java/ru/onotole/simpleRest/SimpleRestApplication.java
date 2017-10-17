package ru.onotole.simpleRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.onotole.simpleRest.entity.VelobikeApiUrl;

@SpringBootApplication
@EnableConfigurationProperties(VelobikeApiUrl.class)
public class SimpleRestApplication {
	public static void main(String[] args) {
		SpringApplication.run(SimpleRestApplication.class, args);
	}
}
