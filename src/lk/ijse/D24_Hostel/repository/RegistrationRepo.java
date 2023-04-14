package lk.ijse.D24_Hostel.repository;

import javafx.scene.control.Alert;
import lk.ijse.D24_Hostel.dto.ReservationDTO;
import lk.ijse.D24_Hostel.entity.Reservation;
import lk.ijse.D24_Hostel.entity.Room;
import lk.ijse.D24_Hostel.entity.Student;
import lk.ijse.D24_Hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RegistrationRepo {
    public List<String> loadId() {
        Session session = FactoryConfiguration.getInstance().getSession();

        List<String> list = session.createQuery("SELECT res_id FROM Reservation ORDER BY res_id DESC ").list();
        return list;
    }

    public List<Room> loadRoomIDs() {
        Session session = FactoryConfiguration.getInstance().getSession();
        List list = session.createQuery("from Room ").list();
        return list;
    }


    public boolean save(Reservation reservation) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(reservation);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            Alert alert = new Alert(Alert.AlertType.WARNING, "Failed..!");
            alert.show();
        } finally {
            session.close();
        }
        return false;
    }


    public List<Reservation> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            return session.createQuery("from Reservation ").list();
        } catch (Exception e) {
            transaction.rollback();
            Alert alert = new Alert(Alert.AlertType.ERROR, "An error occurred in ReservationREPO!");
            alert.show();
        } finally {
            session.close();
        }
        return null;
    }

    public List<String> loadStudentId() {
        Session session = FactoryConfiguration.getInstance().getSession();

        List<String> list = session.createQuery("SELECT student_id FROM Student ").list();
        return list;
    }
}

