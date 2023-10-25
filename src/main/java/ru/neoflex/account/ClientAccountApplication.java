package ru.neoflex.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ClientAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientAccountApplication.class, args);
	}

}
