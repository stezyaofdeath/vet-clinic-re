package com.addicted2u.commands;

import com.addicted2u.entitys.ServiceEntity;
import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class GetAllMedicalServices implements Command {
    private Session session;

    public GetAllMedicalServices(Session session) {
        this.session = session;
    }

    @Override
    public Object execute() {
        Transaction transaction = session.beginTransaction();

        List services = session.createQuery("FROM ServiceEntity").list();

        transaction.commit();

        return new Gson().toJson(services);
    }
}
