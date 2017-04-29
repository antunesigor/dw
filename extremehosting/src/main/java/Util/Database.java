package Util;

import DAO.UserDAO;
import Model.Rate;
import Model.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JList;

public class Database {

    public static UserDAO FillDB(){
        
        UserDAO dao = new UserDAO();
        
        User user = new User();
        user.setName("Igor Antunes");
        user.setCity("Rio de Janeiro");
        user.setCountry("Brazil");
        user.setHost(true);
        dao.insert(user);
        
        User user2 = new User();
        user2.setName("Igor Blackman");
        user2.setCity("Niterói");
        user2.setCountry("Brazil");
        user2.setHost(true);
        dao.insert(user2);
        
        User user3 = new User();
        user3.setName("Paulo Henrique Borges");
        user3.setCity("Nova Friburgo");
        user3.setCountry("Brazil");
        user3.setHost(false);
        dao.insert(user3);
        
        
        Rate rate = new Rate();
        rate.setSender(user);
        rate.setReceiver(user2);
        rate.setDescription("Ótima pessoa");
        rate.setType(0);
        rate.setValue(4);
        rate.setCreated(new Date());
        dao.insert(rate);
        
        rate = new Rate();
        rate.setSender(user3);
        rate.setReceiver(user2);
        rate.setDescription("Ótimo Host");
        rate.setType(1);
        rate.setValue(5);
        rate.setCreated(new Date());
        dao.insert(rate);
        
        rate = new Rate();
        rate.setSender(user);
        rate.setReceiver(user2);
        rate.setDescription("Péssimo Guest");
        rate.setType(2);
        rate.setValue(2);
        rate.setCreated(new Date());
        dao.insert(rate);
        
        return dao;
    }
    
}
