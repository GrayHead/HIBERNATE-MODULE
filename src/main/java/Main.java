import models.Card;
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
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(serviceRegistry).addAnnotatedClass(User.class).addAnnotatedClass(Passport.class).addAnnotatedClass(Card.class).getMetadataBuilder().build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

//        session.save(new User("petya", Gender.MALE, Arrays.asList("java", "js", "html"), new Passport("pihfigdfgsdf")));
//        session.save(new User("vasya", Gender.MALE, Arrays.asList("java SE", "mongo", "js"), new Passport("phvsgdie")));
        User user = new User("olya", Gender.FEMALE, Arrays.asList("java", "js", "html"), new Passport("dshfigfsd"));
        System.out.println(user);
        session.save(user);
        System.out.println(user);

        Card card = new Card("7615634127357", user);
        session.save(card);

        session.getTransaction().commit();


        session.createQuery("select c from Card c",Card.class).getResultList()
                        .forEach(card1 -> System.out.println(card1.getUser().getName()));


        session.close();
        sessionFactory.close();


    }
}
