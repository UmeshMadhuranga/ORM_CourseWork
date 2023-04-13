package lk.ijse.D24_Hostel.dao.custom;

import lk.ijse.D24_Hostel.dao.util.SuperDao;
import lk.ijse.D24_Hostel.entity.Room;
import lk.ijse.D24_Hostel.entity.Student;

import java.util.List;

public interface RoomsDAO extends SuperDao {

    boolean add(Room room);
    Room search(String id);
    boolean update(Room room);
    boolean delete(String id);
    List<Room> getAll();

}
