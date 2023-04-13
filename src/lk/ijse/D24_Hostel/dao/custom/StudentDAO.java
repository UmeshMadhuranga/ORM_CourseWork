package lk.ijse.D24_Hostel.dao.custom;

import lk.ijse.D24_Hostel.dao.util.SuperDao;
import lk.ijse.D24_Hostel.entity.Student;

import java.util.List;

public interface StudentDAO extends SuperDao {

    boolean add(Student student);
    Student search(String id);
    boolean update(Student student);
    boolean delete(String id);
    List<Student> getAll();

}
