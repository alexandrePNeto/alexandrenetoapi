package br.edu.infnet.alexandrenetoapi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.alexandrenetoapi.model.domain.Car;
import br.edu.infnet.alexandrenetoapi.model.domain.service.CarService;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public Car includeCar(@RequestBody Car entity) {
        return this.carService.create(entity);
    }

    @GetMapping
    public ArrayList<Car> carList() {
        return carService.readAll();
    }
}
