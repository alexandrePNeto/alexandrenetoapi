package br.edu.infnet.alexandrenetoapi.model.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

@Entity
public class Client extends Person {
    public Client() { }

    public Client(String name, String cpf) {
        setName(name);
        setCpf(cpf);
    }

    @OneToMany(
        mappedBy = "client",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.EAGER
    )
    private List<Car> cars;

    @Override
    public String toString() {
        return String.format(
            "Client=[%s, cars=%s]",
            super.toString(),
            cars.toString()
        );
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
