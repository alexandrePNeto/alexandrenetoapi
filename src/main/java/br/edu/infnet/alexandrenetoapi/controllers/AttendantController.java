package br.edu.infnet.alexandrenetoapi.controllers;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.infnet.alexandrenetoapi.model.domain.Attendant;
import br.edu.infnet.alexandrenetoapi.model.service.AttendantService;

@RestController
@RequestMapping("/api/attendants")
public class AttendantController {
    private final AttendantService attendantService;

    public AttendantController(AttendantService attendantService) {
        this.attendantService = attendantService;
    }

    @PostMapping
    public ResponseEntity<Attendant> includeAttendant(@RequestBody Attendant attendant) {
        return ResponseEntity.status(HttpStatus.CREATED).body(attendantService.create(attendant));
    }

    @GetMapping
    public ResponseEntity<ArrayList<Attendant>> attendantList() {
        ArrayList<Attendant> attendants = attendantService.readAll();

        if(attendants.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(attendants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendant> carById(@PathVariable Integer id) {
        return ResponseEntity.ok(attendantService.readById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable Integer id) {
        attendantService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attendant> updateCar(@PathVariable Integer id, @RequestBody Attendant entity) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(attendantService.update(id, entity));
    }

    // TODO, criar um método que faça uma consulta específica usando o queryMethod
}
