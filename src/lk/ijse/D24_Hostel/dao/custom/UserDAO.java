package lk.ijse.D24_Hostel.dao.custom;

import lk.ijse.D24_Hostel.dao.util.SuperDao;
import lk.ijse.D24_Hostel.entity.User;

import java.util.List;

public interface UserDAO extends SuperDao {
    List<User> getAll();
    boolean addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(String text);

    User searchUser(String text);
}
