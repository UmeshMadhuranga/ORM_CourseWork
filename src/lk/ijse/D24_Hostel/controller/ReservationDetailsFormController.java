package lk.ijse.D24_Hostel.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.D24_Hostel.dto.ReservationDTO;
import lk.ijse.D24_Hostel.dto.StudentDTO;
import lk.ijse.D24_Hostel.entity.Reservation;
import lk.ijse.D24_Hostel.entity.Student;
import lk.ijse.D24_Hostel.service.impl.RegistrationServiceImpl;
import lk.ijse.D24_Hostel.service.util.ServiceFactory;
import lk.ijse.D24_Hostel.service.util.ServiceTypes;

import java.time.LocalDate;
import java.util.List;

public class ReservationDetailsFormController {
    public Label lblDate;

    public TableView tblReservationDetails;

    public TableColumn colDate;
    public TableColumn colRes_Id;
    public TableColumn colRoomId;
    public TableColumn colStudentId;
    public TableColumn colStudentName;
    public TableColumn colRoomType;
    public TableColumn colStatus;

    public void initialize() {
        loadTableData();

        colDate.setCellValueFactory(new PropertyValueFactory<Reservation, LocalDate>("student_id"));
        colRes_Id.setCellValueFactory(new PropertyValueFactory<Reservation, String>("res_id"));
        colRoomId.setCellValueFactory(new PropertyValueFactory<Reservation,String>("room_type_id"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<Reservation,String>("student_id"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<Reservation,String>("student_id"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<Reservation,String>("student_id"));
        colStatus.setCellValueFactory(new PropertyValueFactory<Reservation,String>("status"));

    }

    private void loadTableData() {
        RegistrationServiceImpl registrationService = (RegistrationServiceImpl) ServiceFactory.getService(ServiceTypes.RESERVATION);
        List<ReservationDTO> list = registrationService.getAll();

        ObservableList<ReservationDTO> observableList = FXCollections.observableArrayList();

        for (ReservationDTO reservationDTO: list) {
            observableList.add(reservationDTO);
        }
        tblReservationDetails.setItems(observableList);
    }

    public void imgSettingOnMouseClicked(MouseEvent mouseEvent) {
    }
}
