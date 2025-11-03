package br.edu.infnet.alexandrenetoapi.model.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
public class Attendant extends Person {
    private String registration;

    @Transient
    private String cep;

    @Embedded
    private Address address;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCep() {
        return address != null ? address.getCep() : cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
