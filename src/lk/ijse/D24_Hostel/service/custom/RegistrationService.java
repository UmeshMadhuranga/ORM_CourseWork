package lk.ijse.D24_Hostel.service.custom;

import lk.ijse.D24_Hostel.dto.ReservationDTO;
import lk.ijse.D24_Hostel.dto.RoomDTO;
import lk.ijse.D24_Hostel.service.util.SuperService;

import java.util.List;

public interface RegistrationService extends SuperService {
    List<String> loadId();

    List<RoomDTO> loadRoomId();

    boolean saveRegistration(ReservationDTO reservationDTO);

    List<ReservationDTO> getAll();

    List<String> loadStudentIDs();

    boolean updateRoom(RoomDTO roomDTO1);
}
