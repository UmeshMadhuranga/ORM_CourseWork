package lk.ijse.D24_Hostel.dao.custom;

import lk.ijse.D24_Hostel.dao.util.SuperDao;
import lk.ijse.D24_Hostel.entity.Reservation;
import lk.ijse.D24_Hostel.entity.Room;

import java.util.List;

public interface RegistrationDAO extends SuperDao {

    List<String> loadId();
    List<Room> loadRoomIds();

    boolean save(Reservation reservation);

    List<Reservation> getAll();

    List<String> loadStudentId();
}
