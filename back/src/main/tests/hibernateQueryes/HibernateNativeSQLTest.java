package hibernateQueryes;

import com.addicted2u.entitys.DoctorEntity;
import com.addicted2u.sessionFactory.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateNativeSQLTest {
    public static void main(String[] args) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        Transaction transaction = null;
        transaction = session.beginTransaction();

        Query query = session.createSQLQuery(
                "select * from vet_clinic.doctors"
        );
        List<Object[]> doctors = query.list();
        for (Object[] doctor : doctors) {
            DoctorEntity doctorEntity = new DoctorEntity();
            doctorEntity.setID((String) doctor[0]);
            doctorEntity.setName((String) doctor[1]);
            doctorEntity.setPatronum((String) doctor[2]);

            System.out.println(doctorEntity);
        }

        transaction.commit();
    }
}
