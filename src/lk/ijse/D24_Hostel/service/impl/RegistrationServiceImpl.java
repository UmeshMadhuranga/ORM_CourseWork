package lk.ijse.D24_Hostel.service.impl;

import lk.ijse.D24_Hostel.converter.Convertor;
import lk.ijse.D24_Hostel.dao.impl.RegistrationDAOImpl;
import lk.ijse.D24_Hostel.dao.impl.RoomsDAOImpl;
import lk.ijse.D24_Hostel.dao.util.DaoFactory;
import lk.ijse.D24_Hostel.dao.util.DaoTypes;
import lk.ijse.D24_Hostel.dto.ReservationDTO;
import lk.ijse.D24_Hostel.dto.RoomDTO;
import lk.ijse.D24_Hostel.service.custom.RegistrationService;

import java.util.List;

public class RegistrationServiceImpl implements RegistrationService {
    RegistrationDAOImpl registrationDAO = (RegistrationDAOImpl) DaoFactory.getDAO(DaoTypes.RESERVATION);

    @Override
    public List<String> loadId() {
        return registrationDAO.loadId();
    }

    @Override
    public List<RoomDTO> loadRoomId() {
        return Convertor.toRoomDTOList(registrationDAO.loadRoomIds());
    }

    @Override
    public boolean saveRegistration(ReservationDTO reservationDTO) {
        return registrationDAO.save(Convertor.reservationDTOtoReservation(reservationDTO));
    }

    @Override
    public List<ReservationDTO> getAll() {
        return Convertor.toReservationDTOList(registrationDAO.getAll());
    }

    @Override
    public List<String> loadStudentIDs() {
        return registrationDAO.loadStudentId();
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO1) {
        RoomsDAOImpl roomsDAO = (RoomsDAOImpl) DaoFactory.getDAO(DaoTypes.ROOMS);
        return roomsDAO.update(Convertor.convertRoomDTOtoRoom(roomDTO1));
    }
}
