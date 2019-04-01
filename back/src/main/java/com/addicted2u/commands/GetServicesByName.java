package com.addicted2u.commands;

import com.addicted2u.entitys.ServiceEntity;
import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

public class GetServicesByName implements Command {
    private String nameToSearch;
    private Session session;

    public GetServicesByName(String nameToSearch, Session session) {
        this.nameToSearch = nameToSearch;
        this.session = session;
    }

    public Object execute() {
        // get all data from db
        // search result list with 'nameToSearch'
        // return names and prices
        ArrayList resultList = new ArrayList();

        Transaction transaction = null;

        transaction = session.beginTransaction();
        List services = session.createQuery("FROM ServiceEntity").list();
        for (Object service : services) {
            ServiceEntity serviceEntity = (ServiceEntity) service;
            if (serviceEntity.getName().contains(nameToSearch)) {
                resultList.add(serviceEntity);
            }
        }
        transaction.commit();

        return new Gson().toJson(resultList);
    }
}