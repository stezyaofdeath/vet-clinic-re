package com.addicted2u.commands;

import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetDoctorsByMedService implements Command {
    private Session session;
    private String medService;

    public GetDoctorsByMedService(Session session, String medService) {
        this.session = session;
        this.medService = medService;
    }

    @Override
    public Object execute() {
        Transaction transaction = this.session.beginTransaction();

        Query query = this.session.createSQLQuery(
                "select doc_id, doc_name, doc_surname from medicalservices\n" +
                        "inner join m2m_specialisations_medicalservices on m2m_specialisations_medicalservices.specms_medicalService = medicalservices.ms_id\n" +
                        "inner join specialisations on specialisations.spec_id = m2m_specialisations_medicalservices.specms_specialisation\n" +
                        "inner join m2m_doctors_specialisations on m2m_doctors_specialisations.docspec_specialisation = m2m_specialisations_medicalservices.specms_specialisation\n" +
                        "inner join doctors on doctors.doc_id = m2m_doctors_specialisations.docspec_doctor\n" +
                        "where ms_id =" + this.medService
        );
        List<Object[]> doctors = query.list();

        transaction.commit();

        return new Gson().toJson(doctors);
    }
}
