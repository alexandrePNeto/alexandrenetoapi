package br.edu.infnet.alexandrenetoapi.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.alexandrenetoapi.exceptions.ServiceException;
import br.edu.infnet.alexandrenetoapi.model.domain.ServiceCar;
import br.edu.infnet.alexandrenetoapi.model.repositories.ServiceCarRepository;

@Service
public class ServiceCarService implements CrudService<ServiceCar, Integer> {
    private ServiceCarRepository serviceCarRepository;

    public ServiceCarService(ServiceCarRepository serviceCarRepository) {
        this.serviceCarRepository = serviceCarRepository;
    }

    @Override
    public ServiceCar create(ServiceCar entidade) {
        if (entidade == null) {
            throw new IllegalArgumentException("A entidade ServiceCar não pode ser nula.");
        }
        return serviceCarRepository.save(entidade);
    }

    @Override
    public ArrayList<ServiceCar> readAll() {
        return new ArrayList<>(serviceCarRepository.findAll());
    }

    @Override
    public ServiceCar readById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID não pode ser nulo.");
        }

        return serviceCarRepository.findById(id)
            .orElseThrow(() -> new ServiceException("Associação ServiceCar com ID " + id + " não encontrada."));
    }

    @Override
    public ServiceCar update(Integer id, ServiceCar entidade) {
        readById(id);
        entidade.setId(id);
        return serviceCarRepository.save(entidade);
    }

    @Override
    public void delete(Integer id) {
        ServiceCar serviceCar = readById(id);
        if(serviceCar == null) {
            return;
        }
        serviceCarRepository.delete(serviceCar);
    }

    public List<Integer> findCarsByServiceId(Integer serviceId) {
        if (serviceId == null) {
            throw new IllegalArgumentException("O ID do Service não pode ser nulo.");
        }

        return serviceCarRepository.findCarIdByServiceId(serviceId);
    }
}
