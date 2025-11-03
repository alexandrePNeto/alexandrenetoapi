package br.edu.infnet.alexandrenetoapi.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import br.edu.infnet.alexandrenetoapi.model.domain.Car;
import br.edu.infnet.alexandrenetoapi.model.service.CarService;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<Car> includeCar(@RequestBody Car car) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.create(car));
    }

    @GetMapping
    public ResponseEntity<ArrayList<Car>> carList() {
        ArrayList<Car> carList = carService.readAll();

        if(carList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(carList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> carById(@PathVariable Integer id) {
        return ResponseEntity.ok(carService.readById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable Integer id) {
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Integer id, @RequestBody Car entity) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(carService.update(id, entity));
    }

    @PatchMapping("/{id}/change-color/{color}")
    public ResponseEntity<Car> changeColor(@PathVariable Integer id, @PathVariable String color) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(carService.changeColor(id, color));
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<ArrayList<Car>> carsByColor(@PathVariable String color) {
        ArrayList<Car> carList = carService.readCarsByColor(color);

        if (carList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(carList);
    }

    @GetMapping("/size/{size}")
    public ResponseEntity<ArrayList<Car>> carsBySize(@PathVariable String size) {
        ArrayList<Car> carList = carService.readCarsBySize(size);

        if (carList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(carList);
    }

    @GetMapping("/color/{color}/size/{size}")
    public ResponseEntity<ArrayList<Car>> carsByColorAndSize(@PathVariable String color, @PathVariable String size) {
        ArrayList<Car> carList = carService.readCarsByColorAndSize(color, size);

        if (carList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(carList);
    }

    @GetMapping("/search")
    public ResponseEntity<ArrayList<Car>> searchCarsByName(@RequestParam String name) {
        ArrayList<Car> carList = carService.searchCarsByName(name);

        if (carList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(carList);
    }
}
