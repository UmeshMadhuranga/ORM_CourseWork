package lk.ijse.D24_Hostel.dao.impl;

import lk.ijse.D24_Hostel.dao.custom.UserDAO;
import lk.ijse.D24_Hostel.entity.User;
import lk.ijse.D24_Hostel.repository.UserRepo;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    private UserRepo userRepo = new UserRepo();
    @Override
    public List<User> getAll() {
        return userRepo.getAll();
    }

    @Override
    public boolean addUser(User user) {
        return userRepo.addUser(user);
    }
}
