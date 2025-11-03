package br.edu.infnet.alexandrenetoapi.model.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        if(car == null) {
            throw new IllegalArgumentException("Objeto inválido");
        }

        checkCar(car);

        return carRepository.save(car);
    }

    @Override
    public ArrayList<Car> readAll() {
        return new ArrayList<Car>(carRepository.findAll());
    }

    public ArrayList<Car> getCarsByClientId(Integer clientId) {
        return new ArrayList<Car>(carRepository.findAllByClientId(clientId));
    }

    @Override
    public Car update(Integer id, Car car) {
        if(car == null) {
            throw new IllegalArgumentException("Objeto inválido");
        }

        checkCar(car);

        car.setId(id);

        return carRepository.save(car);
    }

    @Override
    public void delete(Integer id) {
        Car car = readById(id);
        if(car == null) {
            return;
        }

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

    public ArrayList<Car> readCarsByColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            return new ArrayList<>();
        }

        List<Car> resultList = carRepository.findAllByColor(color.trim().toUpperCase());
        return new ArrayList<>(resultList);
    }

    public ArrayList<Car> readCarsBySize(String size) {
        if (size == null || size.trim().isEmpty()) {
            return new ArrayList<>();
        }

        List<Car> resultList = carRepository.findAllBySize(size.trim().toUpperCase());
        return new ArrayList<>(resultList);
    }

    public ArrayList<Car> readCarsByColorAndSize(String color, String size) {
        if (color == null || color.trim().isEmpty() || size == null || size.trim().isEmpty()) {
            return new ArrayList<>();
        }

        List<Car> resultList = carRepository.findAllByColorAndSize(color.trim().toUpperCase(), size.trim().toUpperCase());
        return new ArrayList<>(resultList);
    }

    public ArrayList<Car> searchCarsByName(String nameTerm) {
        if (nameTerm == null || nameTerm.trim().isEmpty()) {
            return new ArrayList<>();
        }

        List<Car> resultList = carRepository.findAllByNameContaining(nameTerm.trim().toUpperCase());
        return new ArrayList<>(resultList);
    }
}
