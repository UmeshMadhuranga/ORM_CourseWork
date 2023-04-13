package lk.ijse.D24_Hostel.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RoomDTO {
    private String room_type_id;
    private String type;
    private double key_money;
    private int qty;
}
