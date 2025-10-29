package br.edu.infnet.alexandrenetoapi.model.domain;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.Valid;

@MappedSuperclass
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

	@Valid
	@JoinColumn(name = "address_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

	@Valid
    @ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "contact_id")
    private Contact contact;


    @Override
    public String toString() {
        return "id=" + id + ", name=" + name + ", address=" + address.toString() + "";
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
    public Contact getContact() {
        return contact;
    }
    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
