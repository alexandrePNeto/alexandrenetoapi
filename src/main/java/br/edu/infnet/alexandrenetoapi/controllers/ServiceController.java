package br.edu.infnet.alexandrenetoapi.controllers;

import br.edu.infnet.alexandrenetoapi.model.domain.Attendant;
import br.edu.infnet.alexandrenetoapi.model.domain.Car;
import br.edu.infnet.alexandrenetoapi.model.domain.Service;
import br.edu.infnet.alexandrenetoapi.model.service.ServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/services") // Define o caminho base
public class ServiceController {
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public ResponseEntity<ArrayList<Service>> serviceList() {
        ArrayList<Service> serviceList = serviceService.readAll();

        if (serviceList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(serviceList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> serviceById(@PathVariable Integer id) {
        return ResponseEntity.ok(serviceService.readById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceById(@PathVariable Integer id) {
        serviceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/attendant/{attendantId}")
    public ResponseEntity<List<Service>> servicesByAttendant(@PathVariable Integer attendantId) {
        Attendant attendantProxy = new Attendant();
        attendantProxy.setId(attendantId);

        List<Service> serviceList;
        try {
            serviceList = serviceService.findAllByAttendant(attendantProxy);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        if (serviceList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(serviceList);
    }

    @GetMapping("/{id}/cars")
    public ResponseEntity<List<Car>> carsByServiceId(@PathVariable Integer id) {
        List<Car> carsList = serviceService.findAllCarsById(id);

        if (carsList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(carsList);
    }
}