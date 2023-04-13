package lk.ijse.D24_Hostel.entity;

import lombok.*;
import org.hibernate.annotations.Columns;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Reservation implements SuperEntity {

    @Id
    private String res_id;
    private LocalDate date;
    private String status;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    public Student student;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "room_type_id")
    public Room room;
}
