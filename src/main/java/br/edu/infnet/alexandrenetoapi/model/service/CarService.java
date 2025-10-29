package br.edu.infnet.alexandrenetoapi.model.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import br.edu.infnet.alexandrenetoapi.exceptions.CarException;
import br.edu.infnet.alexandrenetoapi.model.domain.Car;
import br.edu.infnet.alexandrenetoapi.model.repositories.CarRepository;

@Service
public class CarService implements CrudService<Car, Integer> {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    private void checkCar(Car car) {
        if(car == null) {
            throw new IllegalArgumentException("Objeto inválido");
        }

		if(car.getName() == null || car.getName().trim().isEmpty()) {
			throw new CarException("Nome é obrigatório");
		} else if(car.getPlate() == null || car.getPlate().trim().isEmpty()) {
			throw new CarException("Placa é obrigatória");
        } else if(car.getColor() == null || car.getColor().trim().isEmpty()) {
			throw new CarException("Cor é obrigatória");
        }
    }

    @Override
    public Car create(Car car) {
        checkCar(car);
        return carRepository.save(car);
    }

    @Override
    public ArrayList<Car> readAll() {
        return new ArrayList<Car>(carRepository.findAll());
    }

    @Override
    public Car update(Integer id, Car car) {
        checkCar(car);
        car.setId(id);
        return carRepository.save(car);
    }

    @Override
    public void delete(Integer id) {
        Car car = readById(id);
        carRepository.delete(car);
    }

    public Car changeColor(Integer id, String color) {
        Car car = readById(id);
        car.setColor(color);
        return carRepository.save(car);
    }

    @Override
    public Car readById(Integer id) {
        if(id == null) {
            throw new IllegalArgumentException("Carro não encontrado.");
        }

        return carRepository.findById(id).orElseThrow(() -> new CarException("Carro não encontrado."));
    }
}
