package com.saror.sarorserver;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

public class User {

    private long id;
    @Indexed(unique = true)
    private String phone;
    @Indexed(unique = true)
    private String email;
    private String password;
    private String name;
    private String location;
    private String nationalId;
    private String country;
    private String state;


    public User(){
         new User("+2000000000","ex_email@domain.com", "0000000","Saror name","10.10+l0.15",
                 "123456_id","egypt","saror_state" );

    }
    public User(String phone, String email, String password, String name, String location,
                String nationalId, String country, String state) {
        this.id = new Date().getTime();
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.name = name;
        this.location = location;
        this.nationalId = nationalId;
        this.country = country;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
