package br.edu.infnet.alexandrenetoapi.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.infnet.alexandrenetoapi.model.domain.ServiceCar;
import feign.Param;

public interface ServiceCarRepository extends JpaRepository<ServiceCar, Integer> {
    @Query("SELECT sc.car.id FROM ServiceCar sc WHERE sc.service.id = :serviceId")
    public List<Integer> findCarIdByServiceId(@Param("serviceId") Integer serviceId);
}
