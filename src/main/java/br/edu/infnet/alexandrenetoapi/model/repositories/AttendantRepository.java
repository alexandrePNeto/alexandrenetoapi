package br.edu.infnet.alexandrenetoapi.model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.infnet.alexandrenetoapi.model.domain.Attendant;

@Repository
public interface AttendantRepository extends JpaRepository<Attendant, Integer> {
    Optional<Attendant> findByNameIgnoreCase(String name);
}
