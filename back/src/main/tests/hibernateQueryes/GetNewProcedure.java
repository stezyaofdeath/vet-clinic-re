package hibernateQueryes;

import com.addicted2u.entitys.DoctorEntity;
import com.addicted2u.entitys.ProcedureEntity;
import com.addicted2u.sessionFactory.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class GetNewProcedure {
    public static void main(String[] args) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        Transaction transaction = null;
        // change order status
        transaction = session.beginTransaction();

        ProcedureEntity procedureEntity = session.get(ProcedureEntity.class, 6);
        System.out.println(procedureEntity);

        transaction.commit();
    }
}
