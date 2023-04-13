package lk.ijse.D24_Hostel.service.impl;

import lk.ijse.D24_Hostel.converter.Convertor;
import lk.ijse.D24_Hostel.dao.impl.StudentDAOImpl;
import lk.ijse.D24_Hostel.dao.util.DaoFactory;
import lk.ijse.D24_Hostel.dao.util.DaoTypes;
import lk.ijse.D24_Hostel.dto.StudentDTO;
import lk.ijse.D24_Hostel.service.custom.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentDAOImpl studentDAO = (StudentDAOImpl) DaoFactory.getDAO(DaoTypes.STUDENT);

    @Override
    public boolean addStudent(StudentDTO studentDTO) {
        return studentDAO.add(Convertor.convertStudentDTOToStudent(studentDTO));
    }

    @Override
    public StudentDTO searchStudent(String id) {
        return Convertor.convertStudentToStudentDTO(studentDAO.search(id));
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return Convertor.toStudentDTOList(studentDAO.getAll());
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) {
        return studentDAO.update(Convertor.convertStudentDTOToStudent(studentDTO));
    }

    @Override
    public boolean deleteStudent(String id) {
        return studentDAO.delete(id);
    }
}
