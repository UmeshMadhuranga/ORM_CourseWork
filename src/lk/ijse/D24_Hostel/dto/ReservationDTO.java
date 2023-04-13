package lk.ijse.D24_Hostel.dto;

import lk.ijse.D24_Hostel.entity.Room;
import lk.ijse.D24_Hostel.entity.Student;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReservationDTO {
    private String res_id;
    private LocalDate date;
    private String status;
    public StudentDTO student;
    public RoomDTO room;
}
