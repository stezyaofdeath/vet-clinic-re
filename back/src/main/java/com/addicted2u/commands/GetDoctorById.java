package com.addicted2u.commands;

import com.addicted2u.entitys.DoctorEntity;
import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class GetDoctorById implements Command {
    private Session session;
    private String doctorId;

    public GetDoctorById(Session session, String doctorId) {
        this.session = session;
        this.doctorId = doctorId;
    }

    @Override
    public Object execute() {
        Transaction transaction = null;
        // change order status
        transaction = session.beginTransaction();

        DoctorEntity doctorEntity = this.session.get(DoctorEntity.class, this.doctorId);

        transaction.commit();

        return new Gson().toJson(doctorEntity);
    }
}
