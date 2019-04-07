package com.addicted2u.commands;

import com.addicted2u.entitys.ClientEntity;
import com.addicted2u.entitys.ProcedureEntity;
import com.addicted2u.entitys.ServiceEntity;
import com.addicted2u.sessionFactory.HibernateSessionFactory;
import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ChangeOrderStatus implements Command {
    private Session session;
    private String orderId;
    private int userId;

    public ChangeOrderStatus(Session session, String orderId) {
        this.session = session;
        this.orderId = orderId;
    }

    @Override
    public Object execute() {
        Transaction transaction = null;
        // change order status
        transaction = session.beginTransaction();
        ProcedureEntity procedureEntity = session.get(ProcedureEntity.class, Integer.parseInt(this.orderId));
        this.userId = procedureEntity.getClient();
        if (procedureEntity.isStatus()) {
            procedureEntity.setStatus(false);
        } else {
            procedureEntity.setStatus(true);
        }
        session.update(procedureEntity);
        transaction.commit();

        // reduce user status
        transaction = session.beginTransaction();
        ClientEntity clientEntity = session.get(ClientEntity.class, this.userId);
        //clientEntity.setStat(clientEntity.getStat() + 0.05f);
        //session.update(clientEntity);
        transaction.commit();

        return new Gson().toJson(true);
    }
}
