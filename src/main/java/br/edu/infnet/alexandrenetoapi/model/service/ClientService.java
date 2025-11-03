package br.edu.infnet.alexandrenetoapi.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.infnet.alexandrenetoapi.exceptions.AttendantException;
import br.edu.infnet.alexandrenetoapi.exceptions.ClientException;
import br.edu.infnet.alexandrenetoapi.model.domain.Car;
import br.edu.infnet.alexandrenetoapi.model.domain.Client;
import br.edu.infnet.alexandrenetoapi.model.repositories.ClientRepository;

@Service
public class ClientService implements CrudService<Client, Integer>  {
    private final ClientRepository clientRepository;
    private final CarService carService;

    public ClientService(ClientRepository clientRepository, CarService carService) {
        this.clientRepository = clientRepository;
        this.carService = carService;
    }

    private void checkClient(Client client) {
        if(client == null) {
            throw new IllegalArgumentException("Objeto inválido");
        }

		if(client.getName() == null || client.getName().trim().isEmpty()) {
			throw new AttendantException("Nome é obrigatório");
		}
    }

    @Override
    public Client create(Client client) {
        checkClient(client);
        if(client.getCars() != null) {
            throw new ClientException("Não é permitido cadastrar carros aqui.");
        }

        return clientRepository.save(client);
    }

    @Override
    public ArrayList<Client> readAll() {
        return new ArrayList<Client>(clientRepository.findAll());
    }

    @Override
    public Client update(Integer id, Client client) {
        checkClient(client);
        client.setId(id);
        return clientRepository.save(client);
    }

    @Override
    public void delete(Integer id) {
        Client client = readById(id);

        if(client == null) {
            return;
        }

        clientRepository.delete(client);
    }

    @Override
    public Client readById(Integer id) {
        if(id == null) {
            throw new IllegalArgumentException("Atendente inválido.");
        }

        return clientRepository.findById(id).orElseThrow(
            () -> new AttendantException("Atendente não encontrado.")
        );
    }

    public Client readByCpf(String cpf) {
        if(cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF informado é inváldio");
        }

        return clientRepository.findByCpf(cpf).orElseThrow(
            () -> new ClientException(
                String.format("Cliente não encontrado com o cpf %s.",  cpf)
            )
        );
    }

    public Client readByEmail(String email) {
        if(email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email informado é inváldio");
        }

        return clientRepository.findByEmailIgnoreCase(email).orElseThrow(
            () -> new ClientException(
                String.format("Cliente não encontrado com o email %s.",  email)
            )
        );
    }

    public List<Car> getAllClientCarsById(Integer id) {
        Client client = readById(id);
        return carService.getCarsByClientId(client.getId());
    }
}
