package lk.ijse.D24_Hostel.service.custom;

import lk.ijse.D24_Hostel.dto.StudentDTO;
import lk.ijse.D24_Hostel.service.util.SuperService;

import java.util.List;

public interface StudentService extends SuperService {

    boolean addStudent(StudentDTO studentDTO);
    StudentDTO searchStudent(String id);
    List<StudentDTO> getAllStudents();
    boolean updateStudent(StudentDTO studentDTO);
    boolean deleteStudent(String id);

}
