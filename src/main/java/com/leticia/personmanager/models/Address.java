package com.leticia.personmanager.models;

import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "address")

public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_address;

    @Column(name = "public_place")
    private String publicPlace;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "number")
    private int number;

    @Column(name = "city")
    private String city;

    @Column(name = "main")
    private boolean main;

    @Column(name = "person_id", nullable = true)
    private UUID personId;


    public Address(String publicPlace, String zipCode, int number, String city, boolean main) {
        this.publicPlace = publicPlace;
        this.zipCode = zipCode;
        this.number = number;
        this.city = city;
        this.main = main;
    }

    public Address() {
    }

    public UUID getId_address() {
        return id_address;
    }

    public void setId_address(UUID id_address) {
        this.id_address = id_address;
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public void setPublicPlace(String publicPlace) {
        this.publicPlace = publicPlace;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }

}
