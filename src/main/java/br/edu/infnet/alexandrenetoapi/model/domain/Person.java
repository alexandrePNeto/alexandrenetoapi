package br.edu.infnet.alexandrenetoapi.model.domain;

import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

@MappedSuperclass
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "CPF deve estar no formato 000.000.000-00")
    private String cpf;

    @NotNull
    @PastOrPresent(message = "Data de registro deve ser menor ou igual a atual")
    private LocalDateTime registrerDate;

    @Override
    public String toString() {
        return "id=" + id + ", name=" + name;
    }

    public LocalDateTime getRegistrerDate() {
        return registrerDate;
    }

    public void setRegistrerDate(LocalDateTime registrerDate) {
        this.registrerDate = registrerDate;
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name.toUpperCase();
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
