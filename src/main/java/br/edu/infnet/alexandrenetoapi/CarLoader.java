package br.edu.infnet.alexandrenetoapi;

import org.springframework.stereotype.Component;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.springframework.boot.ApplicationArguments;

import br.edu.infnet.alexandrenetoapi.model.domain.Car;
import br.edu.infnet.alexandrenetoapi.model.service.CarService;
import br.edu.infnet.alexandrenetoapi.model.service.ClientService;

@Order(2)
@Component
public class CarLoader implements ApplicationRunner {
    private final CarService carService;
    private final ClientService clientService;

    public CarLoader(CarService carService, ClientService clientService) {
        this.carService = carService;
        this.clientService = clientService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        FileReader fileRead = new FileReader("carros.csv");
        BufferedReader fileBuffer = new BufferedReader(fileRead);

        while(true) {
            String line = fileBuffer.readLine();

            if(line == null) {
                break;
            }

            Car car = new Car();

            String[] columns = line.split(",");

            car.setName(columns[0]);
            car.setColor(columns[1]);
            car.setSize(columns[2]);
            car.setPlate(columns[3]);
            car.setClient(clientService.readByCpf(columns[4]));

            carService.create(car);
        }

        fileBuffer.close();

        ArrayList<Car> carList = carService.readAll();

        for (Car car : carList) {
            System.out.println(car);
        }
    }
}
