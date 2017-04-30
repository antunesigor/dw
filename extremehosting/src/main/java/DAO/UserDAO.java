package DAO;

import Model.Rate;
import Model.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class UserDAO {

    private SessionFactory sessionFactory;
    public UserDAO(){
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
            configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }
    
    public void insert(User user){
        Session session = sessionFactory.openSession(); 
        session.beginTransaction();
	session.save(user); 
        session.getTransaction().commit();
	session.close();
    }
    
    public void insert(Rate rate){
        Session session = sessionFactory.openSession(); 
        session.beginTransaction();
	session.save(rate); 
        session.getTransaction().commit();
	session.close();
    }
    
    public User getActiveUser(Long id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = (User) session.load(User.class,id);
        return user;
    }
    
    public List<Rate> getRates(Long UserId){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Rate> rates = (List<Rate>) session.createQuery("FROM Rate R where R.Receiver.Id = "+UserId).list();
        return rates;
    }
}
