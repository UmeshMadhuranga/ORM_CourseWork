package lk.ijse.D24_Hostel.repository;

import javafx.scene.control.Alert;
import lk.ijse.D24_Hostel.entity.Room;
import lk.ijse.D24_Hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RoomsRepo {

    public boolean add(Room room) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(room);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
        return false;
    }

    public boolean update(Room room) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(room);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
        return false;
    }

    public boolean delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Room room = session.get(Room.class, id);
            session.delete(room);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
        return false;
    }

    public Room search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            return session.get(Room.class, id);
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    public List<Room> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            return session.createQuery("from Room").list();
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
