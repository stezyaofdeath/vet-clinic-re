package hibernateQueryes;

import com.addicted2u.entitys.ServiceEntity;
import com.addicted2u.entitys.m2m_Doctors_Specialisations;
import com.addicted2u.entitys.m2m_Specialisations_MedicalServicesEntity;
import com.addicted2u.sessionFactory.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class HibernateQueryesTest {
    public static void main(String[] args) {

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        ArrayList doctors = new ArrayList();

        String orderId = "1";

        Transaction transaction = null;
        transaction = session.beginTransaction();
        List services = session.createQuery("" +
                "from m2m_Specialisations_MedicalServicesEntity where specms_medicalServices=" + orderId)
                .list();
        transaction.commit();
        for (Object service : services) {
            transaction = session.beginTransaction();
            List doctorsSpecList = session.createQuery("" +
                    "from m2m_Doctors_Specialisations where docspec_specialisation=" + ((m2m_Specialisations_MedicalServicesEntity)service).getSpecms_specialisation())
                    .list();
            transaction.commit();
            for (Object doctorEnt : doctorsSpecList) {
                transaction = session.beginTransaction();
                List doctorsList = session.createQuery("" +
                        "from DoctorEntity where ID= \'" + ((m2m_Doctors_Specialisations)doctorEnt).getDocspec_doctor() + "\'")
                        .list();
                for (Object doctor : doctorsList) {
                    doctors.add(doctor);
                }
                transaction.commit();
            }
        }
        System.out.println(doctors);
    }
}
