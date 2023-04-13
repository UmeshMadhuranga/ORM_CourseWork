package lk.ijse.D24_Hostel.repository;

import javafx.scene.control.Alert;
import lk.ijse.D24_Hostel.entity.User;
import lk.ijse.D24_Hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserRepo {

    public List<User> getAll(){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {


            List list = session.createQuery("from User").list();
            transaction.commit();
            return list;

        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    public boolean addUser(User user) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
        return false;
    }
}
