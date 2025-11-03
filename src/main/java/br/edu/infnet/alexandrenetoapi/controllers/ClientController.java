package br.edu.infnet.alexandrenetoapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.infnet.alexandrenetoapi.model.domain.Car;
import br.edu.infnet.alexandrenetoapi.model.domain.Client;
import br.edu.infnet.alexandrenetoapi.model.service.ClientService;

@Controller
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService clientService) {
        this.service = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> includeClient(@RequestBody Client client) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(client));
    }

    @GetMapping
    public ResponseEntity<ArrayList<Client>> clientList() {
        ArrayList<Client> clients = service.readAll();

        if(clients.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> clientById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.readById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/cars")
    public ResponseEntity<List<Car>> getAllCarsClientById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.getAllClientCarsById(id));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<Client> getClientByEmail(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.FOUND).body(service.readByEmail(email));
    }
}
