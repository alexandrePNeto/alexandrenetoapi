package br.edu.infnet.alexandrenetoapi.model.domain;

public class Car {
    private String name;

    public Car(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "name=" + this.name;
    }
}
