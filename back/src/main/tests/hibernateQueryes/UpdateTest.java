package hibernateQueryes;

import com.addicted2u.entitys.ProcedureEntity;
import com.addicted2u.entitys.ServiceEntity;
import com.addicted2u.sessionFactory.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UpdateTest {
    public static void main(String[] args) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        Transaction transaction = null;

        transaction = session.beginTransaction();

        ProcedureEntity procedureEntity = session.get(ProcedureEntity.class, 1);
        System.out.println(procedureEntity);
        if (procedureEntity.isStatus()) {
            procedureEntity.setStatus(false);
        } else {
            procedureEntity.setStatus(true);
        }
        session.update(procedureEntity);

        transaction.commit();

    }
}
