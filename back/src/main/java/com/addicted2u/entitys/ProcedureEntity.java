package com.addicted2u.entitys;

import java.util.Date;

public class ProcedureEntity {
    private int id;
    private int client;
    private int medservice;
    private int doctor;
    private Date date;

    public ProcedureEntity() {
    }

    public ProcedureEntity(int client, int medservice, int doctor, Date date) {
        this.client = client;
        this.medservice = medservice;
        this.doctor = doctor;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public int getMedservice() {
        return medservice;
    }

    public void setMedservice(int medservice) {
        this.medservice = medservice;
    }

    public int getDoctor() {
        return doctor;
    }

    public void setDoctor(int doctor) {
        this.doctor = doctor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
