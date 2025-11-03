package br.edu.infnet.alexandrenetoapi.model.domain;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Nome é obrigatório")
    private String name;
    @NotNull(message = "Cor é obrigatória")
    private String color;
    @NotNull(message = "Placa é obrigatória")
    private String plate;

    @Pattern(regexp = "^(HAT|SEDAN|SUV)$", message = "O tipo de carro deve ser HAT, SEDAN ou SUV.")
    private String size;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    public Integer getId() {
        return id;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getPlate() {
        return plate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return ""
            + this.id.toString() + ","
            + "name=" + this.name + ","
            + "color=" + this.color + ","
            + "plate=" + this.plate + ","
            + "size=" + this.size
        ;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
