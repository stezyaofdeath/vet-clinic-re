package hibernateQueryes;

import com.addicted2u.sessionFactory.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClinicStatsQuery {
    public static void main(String[] args) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        ArrayList result = new ArrayList();

        Transaction transaction = null;

        // get data 'bout client
        transaction = session.beginTransaction();

        Query query = session.createSQLQuery(
                "select month(proc_date), sum(proc_realCost) as 'profitForMonth' from procedures\n" +
                        "where year(proc_date) = year(now())\n" +
                        "group by month(proc_date)"
        );
        List<Object[]> profitByMonthses = query.list();

        Map<Integer, String> profitByMonthsesAsMap = new HashMap<>();
        for (int i = 0; i < profitByMonthses.size(); i++) {

            profitByMonthsesAsMap.put(
                    Integer.parseInt(profitByMonthses.get(i)[0].toString()), profitByMonthses.get(i)[1].toString()
            );
        }

        transaction.commit();

        result.add(profitByMonthsesAsMap);

        // the most popular doctor
        transaction = session.beginTransaction();

        query = session.createSQLQuery(
                "select proc_doctor from (\n" +
                        "\tselect *, count(proc_doctor) as 'orders' from procedures\n" +
                        "\tgroup by proc_doctor\n" +
                        ") a\n" +
                        "where orders = (select max(orders) from (\n" +
                        "\tselect *, count(proc_doctor) as 'orders' from procedures\n" +
                        "\tgroup by proc_doctor\n" +
                        ") b)"
        );
        List<Object> mostPopularDoctor = query.list();

        Map<String, String> mostPopularDoctorAsMap = new HashMap<>();
        mostPopularDoctorAsMap.put("doctorest", mostPopularDoctor.get(0).toString());

        transaction.commit();

        result.add(mostPopularDoctorAsMap);

        // the most expencieve order
        transaction = session.beginTransaction();

        query = session.createSQLQuery(
                "select proc_realCost from procedures\n" +
                        "where proc_realCost = (select max(proc_realCost) from procedures)"
        );
        List<Object> mostExpencieveOrder = query.list();

        Map<String, String> mostExpencieveOrderAsMap = new HashMap<>();
        mostExpencieveOrderAsMap.put("expencievest", mostExpencieveOrder.get(0).toString());

        transaction.commit();

        result.add(mostExpencieveOrderAsMap);

        // orders by it specialisations
        transaction = session.beginTransaction();

        query = session.createSQLQuery(
                "select spec_id, spec_name, count(spec_id) as 'num_spec' from procedures\n" +
                        "inner join m2m_specialisations_medicalservices \n" +
                        "  on m2m_specialisations_medicalservices.specms_medicalService = proc_medService\n" +
                        "inner join specialisations \n" +
                        "  on specialisations.spec_id = specms_specialisation\n" +
                        "group by spec_id"
        );
        List<Object[]> orderBySpecialisation = query.list();

        ArrayList specialistions = new ArrayList();
        for (int i = 0; i < orderBySpecialisation.size(); i++) {
            Map<String, String> orderBySpecialisationAsMap = new HashMap<>();

            orderBySpecialisationAsMap.put(
                    "spec_name", orderBySpecialisation.get(i)[1].toString()
            );
            orderBySpecialisationAsMap.put(
                    "spec_count", orderBySpecialisation.get(i)[2].toString()
            );

            specialistions.add(orderBySpecialisationAsMap);
        }

        transaction.commit();

        result.add(specialistions);

        // orders by it services
        transaction = session.beginTransaction();

        query = session.createSQLQuery(
                "select ms_id, ms_name, count(ms_id) as 'num_medserv' from procedures\n" +
                        "inner join m2m_specialisations_medicalservices \n" +
                        "  on m2m_specialisations_medicalservices.specms_medicalService = proc_medService\n" +
                        "inner join medicalServices \n" +
                        "  on medicalServices.ms_id = specms_medicalService\n" +
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

        System.out.println(result);
    }
}
