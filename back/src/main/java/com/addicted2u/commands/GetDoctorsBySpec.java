package com.addicted2u.commands;

import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetDoctorsBySpec implements Command {
    private Session session;
    private String specialisation;

    public GetDoctorsBySpec(Session session, String specialisation) {
        this.session = session;
        this.specialisation = specialisation;
    }

    @Override
    public Object execute() {
        ArrayList resultList = new ArrayList();

        Transaction transaction = null;
        transaction = this.session.beginTransaction();

        Query query = this.session.createSQLQuery(
                "select doc_id, doc_name, doc_surname, group_concat(spec_name) spec_name\n" +
                        "from doctors\n" +
                        "inner join m2m_doctors_specialisations on doc_id = m2m_doctors_specialisations.docspec_doctor\n" +
                        "inner join specialisations on m2m_doctors_specialisations.docspec_specialisation = specialisations.spec_id\n" +
                        "where specialisations.spec_name =\"" + this.specialisation + "\"\n" +
                        "group by doc_id"
        );
        List<Object[]> doctors = query.list();

        for (Object[] doctor : doctors) {
            Map<String, String> doctorAsJson = new HashMap<>();
            doctorAsJson.put("id", doctor[0].toString());
            doctorAsJson.put("name", doctor[1].toString());
            doctorAsJson.put("surname", doctor[2].toString());

            doctorAsJson.put("spec_name", doctor[3].toString());

            resultList.add(doctorAsJson);
        }

        transaction.commit();

        return new Gson().toJson(resultList);
    }
}
