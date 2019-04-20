package hibernateQueryes;

import com.addicted2u.sessionFactory.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientStatsQuery {
    public static void main(String[] args) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        ArrayList result = new ArrayList();

        Transaction transaction = null;

        // get client's spendings stats
        transaction = session.beginTransaction();

        Query query = session.createSQLQuery(
                "select sum(proc_realCost) as 'real_spend', sum(ms_cost) as 'true_spend'\n" +
                        "from procedures\n" +
                        "inner join medicalservices on medicalservices.ms_id = proc_medService\n" +
                        "where proc_client = " + 7
        );
        List<Object[]> profitByMonthses = query.list();

        Map<Integer, String> clientSpendsAsMap = new HashMap<>();
        clientSpendsAsMap.put(0, profitByMonthses.get(0)[0].toString());
        clientSpendsAsMap.put(1, profitByMonthses.get(0)[1].toString());

        System.out.println(clientSpendsAsMap);

        transaction.commit();

        // get the favorite doctor
        transaction = session.beginTransaction();

        query = session.createSQLQuery(
                "select proc_doctor, doc_name, doc_surname from (\n" +
                        "\tselect *, count(proc_doctor) as 'orders' from procedures\n" +
                        "\tinner join vet_clinic.doctors on procedures.proc_doctor = doctors.doc_id\n" +
                        "\twhere proc_client = " + "7" + "\n" +
                        "\tgroup by proc_doctor\n" +
                        ") a\n" +
                        "where orders = (select max(orders) from (\n" +
                        "\tselect *, count(proc_doctor) as 'orders' from procedures\n" +
                        "\tgroup by proc_doctor\n" +
                        ") b)"
        );
        List<Object> mostPopularDoctor = query.list();

        Map<String, String> mostPopularDoctorAsMap = new HashMap<>();
        mostPopularDoctorAsMap.put("doctorest", mostPopularDoctor.toString());

        System.out.println(mostPopularDoctorAsMap);

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
                        "where proc_client = " + "7" + "\n" +
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

        System.out.println(services);

        transaction.commit();

        result.add(services);
    }
}
