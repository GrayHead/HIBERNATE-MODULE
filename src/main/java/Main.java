import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(serviceRegistry).addAnnotatedClass(User.class).addAnnotatedClass(Passport.class).addAnnotatedClass(Card.class).addAnnotatedClass(Sunglass.class).getMetadataBuilder().build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(new User("petya", Gender.MALE, Arrays.asList("java", "js", "html"), new Passport("pihfigdfgsdf"), Arrays.asList(new Card("asyfdyqtfwe7765347"), new Card("87542627")), Arrays.asList(new Sunglass("rayban"), new Sunglass("oko"))));

        session.getTransaction().commit();


        session.close();
        sessionFactory.close();


    }
}
