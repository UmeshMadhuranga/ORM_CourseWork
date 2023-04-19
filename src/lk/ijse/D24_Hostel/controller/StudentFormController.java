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
import javafx.scene.paint.Paint;
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
import java.util.regex.Pattern;

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

    private Pattern studentIDPattern;
    private Pattern studentNamePattern;
    private Pattern addressPattern;
    private Pattern contactPattern;

    public StudentServiceImpl studentService = (StudentServiceImpl) ServiceFactory.getService(ServiceTypes.STUDENT);

    public void initialize() {
        loadStudents();
        lblDate.setText(String.valueOf(LocalDate.now()));

        colStudentId.setCellValueFactory(new PropertyValueFactory<Student,String>("student_id"));
        colName.setCellValueFactory(new PropertyValueFactory<Student,String>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<Student,String>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<Student,String>("contact_no"));
        colDob.setCellValueFactory(new PropertyValueFactory<Student,String>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<Student,String>("gender"));

        studentIDPattern = Pattern.compile("^([S0]{2})([0-9]{2})$");
        studentNamePattern = Pattern.compile("^([\\w\\s\\D][^0-9]{1,})$");
        addressPattern = Pattern.compile("^([A-Za-z0-9\\W\\s]{2,})$");
        contactPattern = Pattern.compile("^[0-7]{3}[0-9]{7}$");
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

        StudentDTO studentDTO = new StudentDTO(
                txtStudentID.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtContact.getText(),
                date,
                gender
                );

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

        boolean isStudentIdMatched = studentIDPattern.matcher(txtStudentID.getText()).matches();
        if (!isStudentIdMatched) {
            txtStudentID.setFocusColor(Paint.valueOf("Red"));
            txtStudentID.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Student ID.").show();
        } else {
            txtName.requestFocus();
        }
    }

    public void txtNameOnAction(ActionEvent actionEvent) {
        boolean isStudentNameMatched = studentNamePattern.matcher(txtName.getText()).matches();
        if (!isStudentNameMatched) {
            txtName.setFocusColor(Paint.valueOf("Red"));
            txtName.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Student Name.").show();
        } else {
            txtAddress.requestFocus();
        }
    }

    public void txtAddressOnAction(ActionEvent actionEvent) {
        boolean isAddressMatched = addressPattern.matcher(txtAddress.getText()).matches();
        if (!isAddressMatched) {
            txtAddress.setFocusColor(Paint.valueOf("Red"));
            txtAddress.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Address.").show();
        } else {
            txtContact.requestFocus();
        }
    }

    public void txtContactOnAction(ActionEvent actionEvent) {
        boolean isContactMatched = contactPattern.matcher(txtContact.getText()).matches();
        if (!isContactMatched) {
            txtContact.setFocusColor(Paint.valueOf("Red"));
            txtContact.requestFocus();
            new Alert(Alert.AlertType.ERROR,"invalid Contact Number.").show();
        } else {
            dtpckDob.requestFocus();
        }
    }

    public void nameOnMouseClicked(MouseEvent mouseEvent) {
        if (txtStudentID.equals("")) {
            new Alert(Alert.AlertType.ERROR,"Student Id can't be null").show();
            txtStudentID.requestFocus();
        }
    }

    public void addressOnMouseClicked(MouseEvent mouseEvent) {
        if (txtName.equals(null)) {
            new Alert(Alert.AlertType.ERROR,"Student name can't be null").show();
            txtName.requestFocus();
        }
    }

    public void contactOnMouseClicked(MouseEvent mouseEvent) {
        if (txtAddress.equals(null)) {
            new Alert(Alert.AlertType.ERROR,"Student name can't be null").show();
            dtpckDob.requestFocus();
        }
    }

    public void tblStudentOnMouseClicked(MouseEvent mouseEvent) {
        StudentDTO studentDTO = (StudentDTO) tblStudent.getSelectionModel().getSelectedItem();

        txtStudentID.setText(studentDTO.getStudent_id());
        txtName.setText(studentDTO.getName());
        txtAddress.setText(studentDTO.getAddress());
        txtContact.setText(studentDTO.getContact_no());
        dtpckDob.setValue(studentDTO.getDob());
    }
}
