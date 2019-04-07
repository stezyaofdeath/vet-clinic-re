package com.addicted2u.commands;

import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetOrdersByClient implements Command {
    private Session session;
    private String clientId;

    public GetOrdersByClient(String clientId, Session session) {
        this.clientId = clientId;
        this.session = session;
    }

    @Override
    public Object execute() {
        ArrayList result = new ArrayList();

        Transaction transaction = null;

        // get data 'bout client
        transaction = session.beginTransaction();

        Query query1 = session.createSQLQuery(
                "select cl_id, cl_name, cl_surname, cl_login, cl_phone\n" +
                        "from vet_clinic.clients\n" +
                        "where cl_id=" + this.clientId
        );
        List<Object[]> clients = query1.list();

        Map<String, String> clientAsMap = new HashMap<>();
        String[] clientKeys = {"cl_id", "cl_name", "cl_surname", "cl_login", "cl_phone"};

        for (int i = 0; i < clients.get(0).length; i++) {
            clientAsMap.put(clientKeys[i], clients.get(0)[i].toString());
        }

        result.add(clientAsMap);

        transaction.commit();

        // get data 'bout client's orders

        transaction = session.beginTransaction();

        Query query = session.createSQLQuery(
                "select doc_id, doc_name, doc_surname, ms_name, ms_cost, proc_date, proc_status\n" +
                        "from procedures\n" +
                        "inner join doctors on proc_doctor = doctors.doc_id\n" +
                        "inner join medicalservices on proc_medService= medicalservices.ms_id"
        );
        List<Object[]> procedures = query.list();

        ArrayList<Map> resultList = new ArrayList<>();
        String[] keys = {
                "doc_id", "doc_name", "doc_surname", "ms_name", "ms_cost", "proc_date", "proc_status"
        };

        for(Object[] procedure : procedures) {
            Map<String, String> assocProcedure = new HashMap<>();
            for (int i = 0; i < procedure.length; i++) {
                assocProcedure.put(keys[i], procedure[i].toString());
            }
            resultList.add(assocProcedure);
        }

        result.add(resultList);

        transaction.commit();

        return (new Gson()).toJson(result);
    }
}
