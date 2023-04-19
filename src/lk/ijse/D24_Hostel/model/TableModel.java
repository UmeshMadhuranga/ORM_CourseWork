package lk.ijse.D24_Hostel.model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TableModel {
    private LocalDate date;
    private String reservationId;
    private String roomId;
    private String studentId;
    private String studentName;
    private String roomType;
    private String status;
}
