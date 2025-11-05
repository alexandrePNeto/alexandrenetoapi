package br.edu.infnet.alexandrenetoapi.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.infnet.alexandrenetoapi.model.domain.Attendant;
import br.edu.infnet.alexandrenetoapi.model.domain.Service;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
    public List<Service> findAllByAttendant(Attendant attendant);
}
