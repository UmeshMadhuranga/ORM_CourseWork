package lk.ijse.D24_Hostel.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements SuperEntity {
    @Id
    private String userName;
    private String password;
}
