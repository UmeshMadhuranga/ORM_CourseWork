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

    @Override
    public boolean updateUser(User user) {
        return userRepo.update(user);
    }

    @Override
    public boolean deleteUser(String text) {
        return userRepo.delete(text);
    }

    @Override
    public User searchUser(String text) {
        return userRepo.search(text);
    }
}
