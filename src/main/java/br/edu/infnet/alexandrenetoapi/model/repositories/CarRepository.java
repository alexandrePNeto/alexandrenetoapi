package br.edu.infnet.alexandrenetoapi.model.repositories;

import br.edu.infnet.alexandrenetoapi.model.domain.Car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> { }
