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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Passport.class)
                .addAnnotatedClass(Card.class)
                .getMetadataBuilder().build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(new User("petya",
                Gender.MALE, Arrays.asList("java", "js", "html"),
                new Passport("pihfigdfgsdf"),
                Arrays.asList(new Card("asyfdyqtfwe7765347"), new Card("87542627"))
        ));

        session.getTransaction().commit();

        session.createQuery("select u from User u",User.class)
                        .getResultList()
                                .forEach(user -> System.out.println(user.getCards().get(0).getNumber()));

        Set<User> collect = new HashSet<>(session.createQuery("select c.user from Card c", User.class)
                .getResultList());

        System.out.println(collect);


        session.close();
        sessionFactory.close();


    }
}
