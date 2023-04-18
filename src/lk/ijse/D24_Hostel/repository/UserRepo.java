package lk.ijse.D24_Hostel.repository;

import javafx.scene.control.Alert;
import lk.ijse.D24_Hostel.entity.Student;
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

    public boolean update(User user) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
        return false;
    }

    public boolean delete(String text) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = session.get(User.class, text);
            session.delete(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Occurred..!");
            alert.show();
        } finally {
            session.close();
        }
        return false;
    }

    public User search(String text) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            return session.get(User.class, text);
        } catch (Exception e) {
            transaction.rollback();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Occurred..!");
            alert.show();
        } finally {
            session.close();
        }
        return null;
    }
}
