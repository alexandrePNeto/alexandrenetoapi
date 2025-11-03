package br.edu.infnet.alexandrenetoapi.model.domain;

import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String cpf;

    @Override
    public String toString() {
        return "id=" + id + ", name=" + name;
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
