package br.edu.infnet.alexandrenetoapi.model.domain;

import jakarta.persistence.Entity;

@Entity
public class Attendant extends Person {
    private String registration;

    @Override
    public String toString() {
        return "Attendant [registration=" + registration + ","+ super.toString() +"]";
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }
}
