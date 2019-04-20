package hibernateQueryes;

import com.addicted2u.sessionFactory.HibernateSessionFactory;
import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GetAllMSQuery {
    public static void main(String[] args) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        List services = session.createQuery("FROM ServiceEntity").list();
        transaction.commit();

        System.out.println(new Gson().toJson(services));
    }
}
