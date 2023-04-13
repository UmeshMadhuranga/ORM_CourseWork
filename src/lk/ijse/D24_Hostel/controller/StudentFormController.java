package lk.ijse.D24_Hostel.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.D24_Hostel.dto.StudentDTO;
import lk.ijse.D24_Hostel.entity.Student;
import lk.ijse.D24_Hostel.service.impl.StudentServiceImpl;
import lk.ijse.D24_Hostel.service.util.ServiceFactory;
import lk.ijse.D24_Hostel.service.util.ServiceTypes;
import lk.ijse.D24_Hostel.util.Navigation;
import lk.ijse.D24_Hostel.util.Routes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class StudentFormController {
    public AnchorPane pane;

    public Label lblDate;

    public TableView tblStudent;

    public TableColumn colStudentId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colDob;
    public TableColumn colGender;

    public JFXTextField txtStudentID;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXDatePicker dtpckDob;
    public ToggleGroup genderGroup;

    public StudentServiceImpl studentService = (StudentServiceImpl) ServiceFactory.getService(ServiceTypes.STUDENT);

    public void initialize() {
        loadStudents();

        colStudentId.setCellValueFactory(new PropertyValueFactory<Student,String>("student_id"));
        colName.setCellValueFactory(new PropertyValueFactory<Student,String>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<Student,String>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<Student,String>("contact_no"));
        colDob.setCellValueFactory(new PropertyValueFactory<Student,String>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<Student,String>("gender"));
    }

    private void loadStudents() {
        ObservableList<StudentDTO> observableList = FXCollections.observableArrayList();

        List<StudentDTO> allStudents = studentService.getAllStudents();
        for (StudentDTO studentDTO: allStudents) {
            observableList.add(studentDTO);
        }
        tblStudent.setItems(observableList);
    }

    public void btnAddStudentOnAction(ActionEvent actionEvent) {
        RadioButton radioButton = (RadioButton) genderGroup.getSelectedToggle();
        String gender = radioButton.getText();

        LocalDate date = dtpckDob.getValue();
//        Date date = new Date();

        StudentDTO studentDTO = new StudentDTO(
                txtStudentID.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtContact.getText(),
                date,
                gender
                );

//        System.out.println(studentDTO);

        boolean isAdded = studentService.addStudent(studentDTO);
        if (isAdded) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Student added successfully.");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Something wrong.!");
            alert.show();
        }
    }

    public void btnUpdateStudentOnAction(ActionEvent actionEvent) {
        RadioButton radioButton = (RadioButton) genderGroup.getSelectedToggle();
        String gender = radioButton.getText();

        LocalDate date = dtpckDob.getValue();

        StudentDTO studentDTO = new StudentDTO(
                txtStudentID.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtContact.getText(),
                date,
                gender
        );

        boolean isUpdated = studentService.updateStudent(studentDTO);
        if (isUpdated) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Operation successfully done..");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Operation Failed..");
            alert.show();
        }
    }

    public void btnDeleteStudentOnAction(ActionEvent actionEvent) {
        boolean isDeleted = studentService.deleteStudent(txtStudentID.getText());
        if (isDeleted) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Operation successfully done..");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Operation Failed..");
            alert.show();
        }
    }

    public void btnSearchStudentOnAction(ActionEvent actionEvent) {
        StudentDTO studentDTO = studentService.searchStudent(txtStudentID.getText());

        if (studentDTO != null) {
            txtName.setText(studentDTO.getName());
            txtAddress.setText(studentDTO.getAddress());
            txtContact.setText(studentDTO.getContact_no());
            dtpckDob.setValue(null);
        } else if (studentDTO == null){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Student not Found.");
            alert.show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.STUDENT, pane);
    }

    public void imgSettingOnMouseClicked(MouseEvent mouseEvent) {
    }

    public void txtStudentIDOnAction(ActionEvent actionEvent) {
        btnSearchStudentOnAction(actionEvent);
    }
}
