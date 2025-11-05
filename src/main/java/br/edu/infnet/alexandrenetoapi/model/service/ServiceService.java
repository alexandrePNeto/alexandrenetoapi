package br.edu.infnet.alexandrenetoapi.model.service;

import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.alexandrenetoapi.exceptions.ServiceException;
import br.edu.infnet.alexandrenetoapi.model.domain.Attendant;
import br.edu.infnet.alexandrenetoapi.model.domain.Car;
import br.edu.infnet.alexandrenetoapi.model.domain.Service;
import br.edu.infnet.alexandrenetoapi.model.repositories.ServiceRepository;

@org.springframework.stereotype.Service
public class ServiceService implements CrudService<Service, Integer> {
    private CarService carService;
    private ServiceRepository serviceRepository;
    private ServiceCarService serviceCarService;

    public ServiceService(
        ServiceRepository serviceRepository,
        ServiceCarService serviceCarService,
        CarService carService
    ) {
        this.carService = carService;
        this.serviceRepository = serviceRepository;
        this.serviceCarService = serviceCarService;
    }

    @Override
    public Service create(Service entidade) {
        if (entidade == null) {
            throw new IllegalArgumentException("A entidade Service não pode ser nula.");
        }
        return serviceRepository.save(entidade);
    }

    @Override
    public ArrayList<Service> readAll() {
        return new ArrayList<Service>(serviceRepository.findAll());
    }

    @Override
    public Service readById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo.");
        }

        return serviceRepository.findById(id)
            .orElseThrow(() -> new ServiceException("Serviço com ID " + id + " não encontrado."));
    }

    @Override
    public Service update(Integer id, Service entidade) {
        readById(id);
        entidade.setId(id);
        return serviceRepository.save(entidade);
    }

    @Override
    public void delete(Integer id) {
        Service service = readById(id);

        if(service == null) {
            return;
        }

        serviceRepository.delete(service);
    }

    public List<Service> findAllByAttendant(Attendant attendant) {
        if (attendant == null || attendant.getId() == null) {
            throw new IllegalArgumentException("O Atendente ou o ID do Atendente não podem ser nulos.");
        }

        return serviceRepository.findAllByAttendant(attendant);
    }


    public List<Car> findAllCarsById(Integer id) {
        Service service = readById(id);
        List<Car> cars = new ArrayList<Car>();

        for(Integer carId : serviceCarService.findCarsByServiceId(service.getId())) {
            cars.add(carService.readById(carId));
        }

        return cars;
    }
}
