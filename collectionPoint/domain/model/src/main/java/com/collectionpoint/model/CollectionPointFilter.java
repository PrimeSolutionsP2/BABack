package com.collectionpoint.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class CollectionPointFilter {

    public CollectionPointFilter(String idCard, String name, String email, String address, String city, String state, String country, String status) {
        this.idCard = idCard;
        this.name = name;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.status = status;
    }

    public String idCard;
    public String name;
    public String email;
    public String address;
    public String city;
    public String state;
    public String country;
    public String status;

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
