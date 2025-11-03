package br.edu.infnet.alexandrenetoapi.model.repositories;

import br.edu.infnet.alexandrenetoapi.model.domain.Car;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    public List<Car> findAllByClientId(Integer id);
    public List<Car> findAllByColor(String color);
    public List<Car> findAllBySize(String size);
    public List<Car> findAllByColorAndSize(String color, String size);
    public List<Car> findAllByNameContaining(String nameTerm);
}
