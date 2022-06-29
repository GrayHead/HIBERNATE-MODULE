import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(User.class)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

//        User user = new User("petya");
//        User user2 = new User("kokos");
//        User user3 = new User("abrikos");
//        session.save(user);
//        session.save(user2);
//        session.save(user3);


//        User user = session.find(User.class, 1);
//        System.out.println(user);

//        List<User> list = session.createNativeQuery("select * from  user", User.class).list();
        List<User> list = session.createQuery("select u from User u", User.class).list();
        System.out.println(list);

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();


    }
}
