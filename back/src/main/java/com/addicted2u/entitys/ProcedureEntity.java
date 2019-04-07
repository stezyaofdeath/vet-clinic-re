package com.addicted2u.entitys;

import java.util.Date;

public class ProcedureEntity {
    private int id;
    private int client;
    private int medservice;
    private String doctor;
    private Date date;
    private boolean status;

    public ProcedureEntity() {
    }

    public ProcedureEntity(int client, int medservice, String doctor, Date date, boolean status) {
        this.client = client;
        this.medservice = medservice;
        this.doctor = doctor;
        this.date = date;
        this.status = status;
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

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProcedureEntity{" +
                "id=" + id +
                ", client=" + client +
                ", medservice=" + medservice +
                ", doctor=" + doctor +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}
