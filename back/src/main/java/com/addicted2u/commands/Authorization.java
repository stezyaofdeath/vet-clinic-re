package com.addicted2u.commands;

import com.addicted2u.entitys.ClientEntity;
import com.addicted2u.entitys.ServiceEntity;
import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Authorization implements Command {
    private String login;
    private String password;
    private Session session;

    public Authorization(String login, String password, Session session) {
        this.login = login;
        this.password = password;
        this.session = session;
    }

    @Override
    public Object execute() {
        ClientEntity clientEntityResult = null;

        Transaction transaction = null;

        transaction = session.beginTransaction();
        List services = session.createQuery("FROM ClientEntity ").list();
        for (Object service : services) {
            ClientEntity clientEntity = (ClientEntity) service;
            if (clientEntity.getLogin().equals(this.login) && clientEntity.getPassword().equals(this.password)) {
                clientEntityResult = clientEntity;
            }
        }
        transaction.commit();

        return new Gson().toJson(clientEntityResult);
    }
}
