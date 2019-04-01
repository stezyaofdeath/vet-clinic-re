package com.addicted2u.entitys;

import java.io.Serializable;

public class DoctorEntity implements Serializable {
    private String ID;
    private String name;
    private String surname;
    private String patronum;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    @Override
    public String toString() {
        return "DoctorEntity{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronum='" + patronum + '\'' +
                '}';
    }
}
