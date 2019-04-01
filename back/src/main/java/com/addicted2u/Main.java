package com.addicted2u;

import com.addicted2u.entitys.DoctorEntity;
import com.addicted2u.entitys.ServiceEntity;
import com.google.gson.Gson;
import org.hibernate.Session;
import com.addicted2u.sessionFactory.HibernateSessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        DoctorEntity doctorsEntity = new DoctorEntity();

        doctorsEntity.setID(3);
        doctorsEntity.setName("Ягарина");
        doctorsEntity.setSurname("Яршо");
        doctorsEntity.setPatronum("Александрович");

        session.save(doctorsEntity);
        session.getTransaction().commit();

        session.close();*/

        /*Session session = HibernateSessionFactory.getSessionFactory().openSession();

        Transaction transaction = null;

        transaction = session.beginTransaction();
        List doctors = session.createQuery("FROM DoctorEntity").list();
        for (Iterator iterator = doctors.iterator(); iterator.hasNext(); ) {
            DoctorEntity doctorEntity = (DoctorEntity)iterator.next();
            System.out.println(doctorEntity);
        }
        transaction.commit();

        session.close();*/

        /*String result = "";

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        Transaction transaction = null;

        transaction = session.beginTransaction();
        List services = session.createQuery("FROM ServiceEntity").list();
        for (Iterator iterator = services.iterator(); iterator.hasNext(); ) {
            ServiceEntity serviceEntity = (ServiceEntity)iterator.next();
            result += serviceEntity.getName() + "|" + serviceEntity.getCost() + ";";
        }
        transaction.commit();

        System.out.println(result);

        //session.close();
        session.getSessionFactory().close();*/

        DoctorEntity doctorEntity1 = new DoctorEntity();

        doctorEntity1.setID("228");
        doctorEntity1.setName("addicted");
        doctorEntity1.setPatronum("Олегович");
        doctorEntity1.setSurname("Yagarina");

        DoctorEntity doctorEntity2 = new DoctorEntity();

        doctorEntity2.setID("228");
        doctorEntity2.setName("addicted");
        doctorEntity2.setPatronum("Олегович");
        doctorEntity2.setSurname("Yagarina");

        DoctorEntity doctorEntity3 = new DoctorEntity();

        doctorEntity3.setID("228");
        doctorEntity3.setName("addicted");
        doctorEntity3.setPatronum("Олегович");
        doctorEntity3.setSurname("Yagarina");

        ArrayList list = new ArrayList();

        list.add(doctorEntity1);
        list.add(doctorEntity2);
        list.add(doctorEntity3);

        Gson gson = new Gson();

        String result = gson.toJson(list);

        System.out.println(result);
    }
}