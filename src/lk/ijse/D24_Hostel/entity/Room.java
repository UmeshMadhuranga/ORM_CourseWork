package lk.ijse.D24_Hostel.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Room implements SuperEntity {
    @Id
    private String room_type_id;
    private String type;
    private double key_money;
    private int qty;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Reservation> roomList;

    public Room(String room_type_id, String type, double key_money, int qty) {
        this.room_type_id = room_type_id;
        this.type = type;
        this.key_money = key_money;
        this.qty = qty;
    }
}
