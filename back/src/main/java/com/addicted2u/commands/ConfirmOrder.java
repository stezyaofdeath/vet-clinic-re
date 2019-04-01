package com.addicted2u.commands;

import com.addicted2u.entitys.ProcedureEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

public class ConfirmOrder implements Command {
    private Session session;
    private ProcedureEntity procedureEntity;

    public ConfirmOrder(ProcedureEntity procedureEntity, Session session) {
        this.procedureEntity = procedureEntity;
        this.session = session;
    }

    @Override
    public Object execute() {
        // insert data from the client side to 'procedures'-table
        Transaction transaction = session.beginTransaction();
        session.save(this.procedureEntity);
        transaction.commit();
        return null;
    }
}
