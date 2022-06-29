import models.Gender;
import models.Passport;
import models.User;
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

        Metadata metadata = new MetadataSources(serviceRegistry).addAnnotatedClass(User.class).getMetadataBuilder().build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(new User("petya", Gender.MALE, Arrays.asList("java","js","html"),new Passport("pihfigdfgsdf")));
        session.save(new User("vasya", Gender.MALE,Arrays.asList("java SE","mongo","js"),new Passport("phvsgdie")));
        session.save(new User("olya", Gender.FEMALE,Arrays.asList("java","js","html"),new Passport("dshfigfsd")));


        session.getTransaction().commit();
        session.close();
        sessionFactory.close();


    }
}
