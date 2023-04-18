package lk.ijse.D24_Hostel.service.impl;

import lk.ijse.D24_Hostel.converter.Convertor;
import lk.ijse.D24_Hostel.dao.impl.UserDAOImpl;
import lk.ijse.D24_Hostel.dao.util.DaoFactory;
import lk.ijse.D24_Hostel.dao.util.DaoTypes;
import lk.ijse.D24_Hostel.dto.UserDTO;
import lk.ijse.D24_Hostel.entity.User;
import lk.ijse.D24_Hostel.service.custom.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAOImpl userDAO = (UserDAOImpl) DaoFactory.getDAO(DaoTypes.USER);

    @Override
    public List<UserDTO> getAll() {
        List<User> list = userDAO.getAll();
        return Convertor.toUserDTOList(list);
    }

    @Override
    public boolean addUser(UserDTO userDTO) {
        return userDAO.addUser(Convertor.convertUserDTOToUser(userDTO));
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        return userDAO.updateUser(Convertor.convertUserDTOToUser(userDTO));
    }

    @Override
    public boolean deleteUser(String text) {
        return userDAO.deleteUser(text);
    }

    @Override
    public UserDTO searchUser(String text) {
        return Convertor.convertUserToUserDTO(userDAO.searchUser(text));
    }
}
