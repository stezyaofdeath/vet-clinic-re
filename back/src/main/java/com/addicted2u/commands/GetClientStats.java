package com.addicted2u.commands;

import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetClientStats implements Command {
    private String clientId;
    private Session session;

    public GetClientStats(String clientId, Session session) {
        this.clientId = clientId;
        this.session = session;
    }

    @Override
    public Object execute() {
        ArrayList result = new ArrayList();

        Transaction transaction = null;

        // get client's spendings stats
        transaction = session.beginTransaction();

        Query query = session.createSQLQuery(
                "select sum(proc_realCost) as 'real_spend', sum(ms_cost) as 'true_spend'\n" +
                        "from procedures\n" +
                        "inner join medicalservices on medicalservices.ms_id = proc_medService\n" +
                        "where proc_client = " + this.clientId
        );
        List<Object[]> profitByMonthses = query.list();

        Map<Integer, String> clientSpendsAsMap = new HashMap<>();
        clientSpendsAsMap.put(0, profitByMonthses.get(0)[0].toString());
        clientSpendsAsMap.put(1, profitByMonthses.get(0)[1].toString());

        transaction.commit();

        result.add(clientSpendsAsMap    );

        // get the favorite doctor
        transaction = session.beginTransaction();

        query = session.createSQLQuery(
                "select proc_doctor, doc_name, doc_surname\n" +
                        "from (\n" +
                        "\tselect *, count(proc_doctor) as 'orders' from procedures\n" +
                        "\tinner join vet_clinic.doctors on procedures.proc_doctor = doctors.doc_id\n" +
                        "    where proc_client = " + this.clientId + "\n" +
                        "    group by proc_doctor\n" +
                        ") a\n" +
                        "where orders = (select max(orders) from (\n" +
                        "\tselect *, count(proc_doctor) as 'orders' from procedures\n" +
                        "    where proc_client = " + this.clientId + "\n" +
                        "    group by proc_doctor\n" +
                        ") b)"
        );
        List<Object[]> mostPopularDoctor = query.list();

        Map<String, String> mostPopularDoctorAsMap = new HashMap<>();
        mostPopularDoctorAsMap.put("doctorest_id", mostPopularDoctor.get(0)[0].toString());
        mostPopularDoctorAsMap.put("doctorest_name", mostPopularDoctor.get(0)[1].toString());
        mostPopularDoctorAsMap.put("doctorest_surname", mostPopularDoctor.get(0)[2].toString());

        transaction.commit();

        result.add(mostPopularDoctorAsMap);

        // get orders structure
        transaction = session.beginTransaction();

        query = session.createSQLQuery(
                "select ms_id, ms_name, count(ms_id) as 'num_medserv' from procedures\n" +
                        "inner join m2m_specialisations_medicalservices \n" +
                        "  on m2m_specialisations_medicalservices.specms_medicalService = proc_medService\n" +
                        "inner join medicalServices \n" +
                        "  on medicalServices.ms_id = specms_medicalService\n" +
                        "where proc_client = " + this.clientId + "\n" +
                        "group by ms_id"
        );
        List<Object[]> orderByService = query.list();

        ArrayList services = new ArrayList();
        for (int i = 0; i < orderByService.size(); i++) {
            Map<String, String> orderByServiceAsMap = new HashMap<>();

            orderByServiceAsMap.put(
                    "serv_name", orderByService.get(i)[1].toString()
            );
            orderByServiceAsMap.put(
                    "serv_count", orderByService.get(i)[2].toString()
            );

            services.add(orderByServiceAsMap);
        }

        transaction.commit();

        result.add(services);
        return new Gson().toJson(result);
    }
}
