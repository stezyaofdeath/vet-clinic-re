package com.addicted2u.commands;

import com.addicted2u.entitys.DoctorEntity;
import com.addicted2u.entitys.ServiceEntity;
import com.addicted2u.entitys.m2m_Doctors_Specialisations;
import com.addicted2u.entitys.m2m_Specialisations_MedicalServicesEntity;
import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class GetOrderInfo implements Command {

    private String orderId;
    private Session session;

    public GetOrderInfo(String orderId, Session session) {
        this.orderId = orderId;
        this.session = session;
    }

    @Override
    public Object execute() {
        ArrayList doctorsResult = new ArrayList();

        Transaction transaction = null;
        transaction = session.beginTransaction();

        // getting info about doctors from the db
        List<Object[]> doctors = session.createSQLQuery(
                "select * from doctors \n" +
                   "inner join m2m_doctors_specialisations on doctors.doc_id = m2m_doctors_specialisations.docspec_doctor\n" +
                   "inner join m2m_specialisations_medicalservices on m2m_doctors_specialisations.docspec_specialisation = m2m_specialisations_medicalservices.specms_specialisation\n" +
                   "where m2m_specialisations_medicalservices.specms_medicalService = " + "\'" + this.orderId + "\';"
        ).list();

        // creating doctor-entityes based on info from the db
        for (Object[] doctor : doctors) {
            DoctorEntity doctorEntity = new DoctorEntity();
            doctorEntity.setID((String) doctor[0]);
            doctorEntity.setName((String) doctor[1]);
            doctorEntity.setPatronum((String) doctor[2]);

            doctorsResult.add(doctorEntity);
        }

        transaction.commit();

        return new Gson().toJson(doctorsResult);
    }
}
