package lk.ijse.D24_Hostel.dao.impl;

import lk.ijse.D24_Hostel.dao.custom.RegistrationDAO;
import lk.ijse.D24_Hostel.dto.ReservationDTO;
import lk.ijse.D24_Hostel.dto.RoomDTO;
import lk.ijse.D24_Hostel.entity.Reservation;
import lk.ijse.D24_Hostel.entity.Room;
import lk.ijse.D24_Hostel.repository.RegistrationRepo;

import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {
    public RegistrationRepo registrationRepo = new RegistrationRepo();

    @Override
    public List<String> loadId() {
        return registrationRepo.loadId();
    }

    @Override
    public List<Room> loadRoomIds() {
        return registrationRepo.loadRoomIDs();
    }

    @Override
    public boolean save(Reservation reservation) {
        return registrationRepo.save(reservation);
    }

    @Override
    public List<Reservation> getAll() {
        return registrationRepo.getAll();
    }

    @Override
    public List<String> loadStudentId() {
        return registrationRepo.loadStudentId();
    }
}
