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

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Passport.class)
                .getMetadataBuilder().build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(new User("petya", Gender.MALE, Arrays.asList("java","js","html"),new Passport("pihfigdfgsdf")));
        session.save(new User("vasya", Gender.MALE,Arrays.asList("java SE","mongo","js"),new Passport("phvsgdie")));
        session.save(new User("olya", Gender.FEMALE,Arrays.asList("java","js","html"),new Passport("dshfigfsd")));


        session.getTransaction().commit();

//        List<User> users = session.createQuery("select u from User u", User.class).getResultList();
//        users.forEach(System.out::println);
//        users.forEach(user -> System.out.println(user.getPassport()));

//        List<Passport> passports = session.createQuery("select u.passport from User u", Passport.class).getResultList();
//        System.out.println(passports);

//        session.createQuery("select p.user from Passport p ",User.class).getResultList().forEach(user -> System.out.println(user));

        List<Passport> passports = session.createQuery("select p from Passport p  join fetch p.user u where p.id=u.id", Passport.class).getResultList();


        for (Passport passport : passports) {
            System.out.println(passport.getUser().getName());
        }

        session.close();
        sessionFactory.close();


    }
}
