package lk.ijse.D24_Hostel.service.impl;

import lk.ijse.D24_Hostel.converter.Convertor;
import lk.ijse.D24_Hostel.dao.impl.RoomsDAOImpl;
import lk.ijse.D24_Hostel.dao.util.DaoFactory;
import lk.ijse.D24_Hostel.dao.util.DaoTypes;
import lk.ijse.D24_Hostel.dto.RoomDTO;
import lk.ijse.D24_Hostel.service.custom.RoomService;

import java.util.List;

public class RoomServiceImpl implements RoomService {
    public RoomsDAOImpl roomsDAO = (RoomsDAOImpl) DaoFactory.getDAO(DaoTypes.ROOMS);

    @Override
    public boolean addRoom(RoomDTO roomDTO) {
        return roomsDAO.add(Convertor.convertRoomDTOtoRoom(roomDTO));
    }

    @Override
    public RoomDTO searchRoom(String id) {
        return Convertor.convertRoomToRoomDTO(roomsDAO.search(id));
    }

    @Override
    public List<RoomDTO> getAllRooms() {
        return Convertor.toRoomDTOList(roomsDAO.getAll());
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) {
        return roomsDAO.update(Convertor.convertRoomDTOtoRoom(roomDTO));
    }

    @Override
    public boolean deleteRoom(String id) {
        return roomsDAO.delete(id);
    }
}
