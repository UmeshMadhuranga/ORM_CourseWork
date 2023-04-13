package lk.ijse.D24_Hostel.repository;

import javafx.scene.control.Alert;
import lk.ijse.D24_Hostel.entity.Student;
import lk.ijse.D24_Hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentRepo {

    public boolean add(Student student) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(student);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Operation failed..!");
            alert.show();
        } finally {
            session.close();
        }
        return false;
    }

    public boolean update(Student student) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.
                beginTransaction();
        try {
            session.update(student);
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

    public boolean delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Student student = session.get(Student.class, id);
            session.delete(student);
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

    public Student search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Student student = session.get(Student.class, id);
            transaction.commit();
            return student;
        } catch (Exception e) {
            transaction.rollback();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error Occurred..!");
            alert.show();
        } finally {
            session.close();
        }
        return null;
    }

    public List<Student> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            return session.createQuery("from Student").list();
        } catch (Exception e) {
            transaction.rollback();
            Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred in RoomsREPO!");
            alert.show();
        } finally {
            session.close();
        }
        return null;
    }
}
