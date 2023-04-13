package lk.ijse.D24_Hostel.dao.impl;

import lk.ijse.D24_Hostel.dao.custom.StudentDAO;
import lk.ijse.D24_Hostel.entity.Student;
import lk.ijse.D24_Hostel.repository.StudentRepo;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    StudentRepo studentRepo = new StudentRepo();

    @Override
    public boolean add(Student student) {
        return studentRepo.add(student);
    }

    @Override
    public Student search(String id) {
        return studentRepo.search(id);
    }

    @Override
    public boolean update(Student student) {
        return studentRepo.update(student);
    }

    @Override
    public boolean delete(String id) {
        return studentRepo.delete(id);
    }

    @Override
    public List<Student> getAll() {
        return studentRepo.getAll();
    }
}
