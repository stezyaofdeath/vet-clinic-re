package hibernateQueryes;

import com.addicted2u.commands.RegisterUser;
import com.addicted2u.entitys.ClientEntity;
import com.addicted2u.sessionFactory.HibernateSessionFactory;
import org.hibernate.Session;

public class UserInserting {
    public static void main(String[] args) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        (new RegisterUser(new ClientEntity(
                "a",
                "a",
                "a",
                "",
                "",
                "",
                ""
        ), session)).execute();
    }
}
