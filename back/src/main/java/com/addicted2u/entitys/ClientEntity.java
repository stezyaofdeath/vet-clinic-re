package com.addicted2u.entitys;

import java.io.Serializable;

public class ClientEntity implements Serializable {
    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String patronum;
    private String phone;
    private String email;

    public ClientEntity() {
    }

    public ClientEntity(String login, String password, String name, String surname, String patronum, String phone, String email) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.patronum = patronum;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronum() {
        return patronum;
    }

    public void setPatronum(String patronum) {
        this.patronum = patronum;
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

    @Override
    public String toString() {
        return "ClientEntity{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronum='" + patronum + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
