package br.edu.infnet.alexandrenetoapi.model.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "serviceCars"})
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @PastOrPresent
    private LocalDateTime emissionTime;
    @FutureOrPresent
    private LocalDateTime dueTime;
    @Min(value = 0)
    private BigDecimal service_value;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "attendant_id", unique = false)
    // @JsonBackReference
    private Attendant attendant;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", unique = false)
    // @JsonBackReference
    private Client client;

    @JsonIgnore
    @OneToMany(mappedBy = "service", orphanRemoval = true)
    private List<ServiceCar> serviceCars;

    public LocalDateTime getEmissionTime() {
        return emissionTime;
    }
    public List<ServiceCar> getServiceCars() {
        return serviceCars;
    }
    public void setServiceCars(List<ServiceCar> serviceCars) {
        this.serviceCars = serviceCars;
    }
    public void setEmissionTime(LocalDateTime emissionTime) {
        this.emissionTime = emissionTime;
    }
    public LocalDateTime getDueTime() {
        return dueTime;
    }
    public void setDueTime(LocalDateTime dueTime) {
        this.dueTime = dueTime;
    }
    public Attendant getAttendant() {
        return attendant;
    }
    public void setAttendant(Attendant attendant) {
        this.attendant = attendant;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public BigDecimal getService_value() {
        return service_value;
    }
    public void setService_value(BigDecimal value) {
        this.service_value = value;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}
