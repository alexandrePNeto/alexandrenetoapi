package br.edu.infnet.alexandrenetoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableFeignClients
@SpringBootApplication
public class AlexandrenetoapiApplication {
	public static void main(String[] args) {
		SpringApplication.run(AlexandrenetoapiApplication.class, args);
	}

}
