package br.edu.infnet.alexandrenetoapi.model.domain.service;

import java.util.Map;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import br.edu.infnet.alexandrenetoapi.model.domain.Car;
import br.edu.infnet.alexandrenetoapi.interfaces.CrudService;

@Service
public class CarService implements CrudService<Car, Integer> {
    private int startId = 1;
    private final Map<Integer, Car> carMap = new ConcurrentHashMap<Integer, Car>();
    private final AtomicInteger newId = new AtomicInteger(startId);

    @Override
    public Car create(Car entity) {
        entity.setId(newId.getAndIncrement());
        carMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public ArrayList<Car> readAll() {
        return new ArrayList<Car>(carMap.values());
    }

    @Override
    public Car update(Integer id, Car entity) {
        if(!carMap.containsKey(id)) {
            return this.create(entity);
        }

        carMap.put(id, entity);

        return entity;
    }

    @Override
    public void delete(Integer id) {
        carMap.remove(id);
    }
}
