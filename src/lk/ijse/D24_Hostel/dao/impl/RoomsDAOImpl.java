package lk.ijse.D24_Hostel.dao.impl;

import lk.ijse.D24_Hostel.dao.custom.RoomsDAO;
import lk.ijse.D24_Hostel.entity.Room;
import lk.ijse.D24_Hostel.repository.RoomsRepo;

import java.util.List;

public class RoomsDAOImpl implements RoomsDAO {
    RoomsRepo roomsRepo = new RoomsRepo();

    @Override
    public boolean add(Room room) {
        return roomsRepo.add(room);
    }

    @Override
    public Room search(String id) {
        return roomsRepo.search(id);
    }

    @Override
    public boolean update(Room room) {
        return roomsRepo.update(room);
    }

    @Override
    public boolean delete(String id) {
        return roomsRepo.delete(id);
    }

    @Override
    public List<Room> getAll() {
        return roomsRepo.getAll();
    }
}
