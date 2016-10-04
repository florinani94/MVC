package com.evozon.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;


@Embeddable
public class Address {
    @Column(insertable=false,updatable=false)
    private String street;

    @Column(insertable=false,updatable=false)
    private Integer number;

    @Column(insertable=false,updatable=false)
    private String city;

    @Column(insertable=false,updatable=false)
    @Pattern(regexp="(^$|[0-9]{10})")
    private String phone;

    public Address(){

    }

    public Address(String street, Integer number, String city, String phone) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + street + '\'' +
                ", number=" + number +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
