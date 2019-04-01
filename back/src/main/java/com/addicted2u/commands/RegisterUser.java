package com.addicted2u.commands;

import com.addicted2u.entitys.ClientEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RegisterUser implements Command {
    private Session session;
    private ClientEntity clientEntity;

    public RegisterUser(ClientEntity clientEntity, Session session) {
        this.clientEntity = clientEntity;
        this.session = session;
    }

    @Override
    public Object execute() {
        Transaction transaction = session.beginTransaction();
        System.out.println(this.clientEntity);
        session.save(this.clientEntity);
        transaction.commit();
        return null;
    }
}
