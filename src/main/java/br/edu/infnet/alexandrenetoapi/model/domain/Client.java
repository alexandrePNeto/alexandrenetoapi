package br.edu.infnet.alexandrenetoapi.model.domain;

import jakarta.persistence.Entity;

@Entity
public class Client extends Person {
    private String plate;

    @Override
    public String toString() {
        return String.format(
            "Client [plate=%s, %s, %s]",
            plate
        );
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}
