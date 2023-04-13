package lk.ijse.D24_Hostel.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student implements SuperEntity {
    @Id
    private String student_id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String address;
    private String contact_no;
    private LocalDate dob;
    private String gender;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Reservation> studentList;

    public Student(String student_id, String name, String address, String contact_no, LocalDate dob, String gender) {
        this.student_id = student_id;
        this.name = name;
        this.address = address;
        this.contact_no = contact_no;
        this.dob = dob;
        this.gender = gender;
    }
}
