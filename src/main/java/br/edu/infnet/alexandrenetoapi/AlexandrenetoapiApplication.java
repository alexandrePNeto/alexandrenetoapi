package br.edu.infnet.alexandrenetoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.infnet.alexandrenetoapi.model.domain.Car;

@SpringBootApplication
public class AlexandrenetoapiApplication {
	public static void main(String[] args) {
		SpringApplication.run(AlexandrenetoapiApplication.class, args);

		Car car = new Car("Nome Inicial");
		System.out.println(car.toString());

		car.setName("Nome Novo");
		System.out.println(car.toString());
	}

}
