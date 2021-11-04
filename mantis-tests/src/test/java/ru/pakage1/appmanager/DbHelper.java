package ru.pakage1.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.pakage1.model.UserData;
import ru.pakage1.model.Users;

import java.util.List;

public class DbHelper {
    private  final SessionFactory sessionFactory;
    private ApplicationManager app;

    public DbHelper(ApplicationManager app) {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        this.app = app;
    }

    public Users userlist(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> result = session.createQuery("from UserData where username !='administrator'").list();
        session.getTransaction().commit();
        session.close();
        return new Users(result);


    }
}

