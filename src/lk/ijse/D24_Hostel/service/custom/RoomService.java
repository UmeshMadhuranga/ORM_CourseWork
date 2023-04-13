package lk.ijse.D24_Hostel.service.custom;

import lk.ijse.D24_Hostel.dto.RoomDTO;
import lk.ijse.D24_Hostel.service.util.SuperService;

import java.util.List;

public interface RoomService extends SuperService {

    boolean addRoom(RoomDTO roomDTO);
    RoomDTO searchRoom(String id);
    List<RoomDTO> getAllRooms();
    boolean updateRoom(RoomDTO roomDTO);
    boolean deleteRoom(String id);

}
