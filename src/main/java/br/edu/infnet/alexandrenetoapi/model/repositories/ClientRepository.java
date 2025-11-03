package br.edu.infnet.alexandrenetoapi.model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.infnet.alexandrenetoapi.model.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByCpf(String cpf);
    Optional<Client> findByEmailIgnoreCase(String email);
}