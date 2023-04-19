package lk.ijse.D24_Hostel.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.D24_Hostel.dto.ReservationDTO;
import lk.ijse.D24_Hostel.dto.RoomDTO;
import lk.ijse.D24_Hostel.dto.StudentDTO;
import lk.ijse.D24_Hostel.service.impl.RegistrationServiceImpl;
import lk.ijse.D24_Hostel.service.impl.RoomServiceImpl;
import lk.ijse.D24_Hostel.service.impl.StudentServiceImpl;
import lk.ijse.D24_Hostel.service.util.ServiceFactory;
import lk.ijse.D24_Hostel.service.util.ServiceTypes;
import lk.ijse.D24_Hostel.util.Navigation;
import lk.ijse.D24_Hostel.util.Routes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class RegisterFormController {
    public AnchorPane pane;

    public Label lblDate;

    public JFXComboBox txtStudentID;
    public Label txtStudentName;
    public Label txtAddress;
    public Label txtContact;
    public Label lblDob;
    public Label lblGender;

    public JFXComboBox cmbRoomType;
    public Label lblRoomId;
    public Label lblKeyMoney;

    public ToggleGroup KeyMoneyGroup;

    public AnchorPane pane1;

    public Label txtRegisterID;
    public Label lblQty;

    public static Stage stage;


    public void initialize() {
        loadRegisterId();
        loadRoomIds();
        loadStudentID();

        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void loadStudentID() {
        RegistrationServiceImpl registrationService = (RegistrationServiceImpl) ServiceFactory.getService(ServiceTypes.RESERVATION);
        List<String> list = registrationService.loadStudentIDs();

        ObservableList<String> studentIdList = FXCollections.observableArrayList();

        for (String id : list) {
            studentIdList.add(id);
        }
        txtStudentID.getItems().setAll(studentIdList);

    }

    private void loadRoomIds() {
//        RegistrationService registrationService = (RegistrationService) ServiceFactory.getService(ServiceTypes.RESERVATION);
//        List<RoomDTO> roomDTOS = registrationService.loadRoomId();

        ObservableList<String> roomIds = FXCollections.observableArrayList();

//        for (RoomDTO roomDTO : roomDTOS) {
//            roomIds.add(roomDTO.getType());
//        }
//        cmbRoomType.getItems().setAll(roomIds);

        roomIds.add("Non-AC");
        roomIds.add("Non-AC/Food");
        roomIds.add("AC");
        roomIds.add("AC/Food");
        cmbRoomType.getItems().setAll(roomIds);

    }

    private void loadRegisterId() {
        RegistrationServiceImpl registrationService = (RegistrationServiceImpl) ServiceFactory.getService(ServiceTypes.RESERVATION);
        List<String> list = registrationService.loadId();

        for (String id: list) {
            String newId = generateNextRegistrationId(id);
            txtRegisterID.setText(newId);
            return;
        }
    }

    private String generateNextRegistrationId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("R00");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "R00" + id;
        } else {
            return "R001";
        }
    }

    public void imgSettingOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass()
                .getResource("/lk/ijse/D24_Hostel/view/UserForm.fxml"))));
        stage.setTitle("User settings");
        stage.show();
    }

    public void btnCheckAgainOnAction(ActionEvent actionEvent) {
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) throws IOException {
        String registerId = txtRegisterID.getText();
        String studentId = (String)txtStudentID.getValue();
        String studentName = txtStudentName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        LocalDate dob = LocalDate.parse(lblDob.getText());
        String gender = lblGender.getText();

        StudentDTO studentDTO = new StudentDTO(studentId, studentName, address, contact, dob, gender);

        //////////////////////////////////////////////////////////////////////////
        String roomType = (String) cmbRoomType.getValue();
        String roomId = lblRoomId.getText();
        double keyMoney = Double.parseDouble(lblKeyMoney.getText());
        int qty = Integer.parseInt(lblQty.getText());

        RoomDTO roomDTO = new RoomDTO(roomId, roomType, keyMoney, qty);

        RegistrationServiceImpl registrationService = (RegistrationServiceImpl) ServiceFactory.getService(ServiceTypes.RESERVATION);

        RadioButton radioButton1 = (RadioButton) KeyMoneyGroup.getSelectedToggle();
        String status = radioButton1.getText();

        ///////////////////////////////////////////////////////////////////////////

        ReservationDTO reservationDTO = new ReservationDTO(
                registerId,
                LocalDate.now(),
                status,
                studentDTO,
                roomDTO
                );

        boolean isAdded = registrationService.saveRegistration(reservationDTO);
        if (isAdded) {

            int newQty = qty-1;
            RoomDTO roomDTO1 = new RoomDTO(roomId, roomType, keyMoney, newQty);

            registrationService.updateRoom(roomDTO1);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Done..!");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Failed..!");
            alert.show();
        }

        txtStudentName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        lblDob.setText("");
        lblGender.setText("");

        lblRoomId.setText("");
        lblKeyMoney.setText("");
        lblQty.setText("");
    }

    public void txtStudentIDOnAction(ActionEvent actionEvent) {
        StudentServiceImpl studentService = (StudentServiceImpl) ServiceFactory.getService(ServiceTypes.STUDENT);

        StudentDTO studentDTO = studentService.searchStudent((String)txtStudentID.getValue());

        if (studentDTO != null) {
            txtStudentName.setText(studentDTO.getName());
            txtAddress.setText(studentDTO.getAddress());
            txtContact.setText(studentDTO.getContact_no());
            txtStudentName.setText(studentDTO.getName());
            lblDob.setText(String.valueOf(studentDTO.getDob()));
            lblGender.setText(studentDTO.getGender());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Student not found");
            alert.show();
        }
    }

    public void imgBackOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN, pane1);
    }

    public void btnManageStudentOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.STUDENT, pane);
    }

    public void btnManageRoomsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ROOMS, pane);
    }

    public void btnRegistrationDetailsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.REGISTRATIONDETAILS, pane);
    }

    public void btnRegistrationOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.REGISTRATION, pane1);
    }

    public void cmbRoomTypeOnAction(ActionEvent actionEvent) {
        String type = (String) cmbRoomType.getValue();

        switch (type) {
            case "Non-AC":
                lblRoomId.setText("RM-1324");
                break;

            case "Non-AC/Food":
                lblRoomId.setText("RM-5467");
                break;

            case "AC":
                lblRoomId.setText("RM-7896");
                break;

            case "AC/Food":
                lblRoomId.setText("RM-0093");
                break;
        }

        String id = lblRoomId.getText();

        RoomServiceImpl roomService = (RoomServiceImpl) ServiceFactory.getService(ServiceTypes.ROOMS);

        RoomDTO roomDTO = roomService.searchRoom(id);

        lblKeyMoney.setText(String.valueOf(roomDTO.getKey_money()));
        lblQty.setText(String.valueOf(roomDTO.getQty()));
    }
}
