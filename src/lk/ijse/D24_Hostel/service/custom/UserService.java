package lk.ijse.D24_Hostel.service.custom;

import lk.ijse.D24_Hostel.dto.UserDTO;
import lk.ijse.D24_Hostel.service.util.SuperService;

import java.util.List;

public interface UserService extends SuperService {
    List<UserDTO> getAll();
    boolean addUser(UserDTO userDTO);

    boolean updateUser(UserDTO userDTO);

    boolean deleteUser(String text);

    UserDTO searchUser(String text);
}
