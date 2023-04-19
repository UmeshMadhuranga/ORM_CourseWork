package lk.ijse.D24_Hostel.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.D24_Hostel.dto.ReservationDTO;
import lk.ijse.D24_Hostel.entity.Reservation;
import lk.ijse.D24_Hostel.model.TableModel;
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

    public static Stage stage;

    public void initialize() {
        lblDate.setText(String.valueOf(LocalDate.now()));

        loadTableData();

        colDate.setCellValueFactory(new PropertyValueFactory<Reservation, LocalDate>("date"));
        colRes_Id.setCellValueFactory(new PropertyValueFactory<Reservation, String>("res_id"));
        colRoomId.setCellValueFactory(new PropertyValueFactory<Reservation,String>("room"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<Reservation,String>("student"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<Reservation,String>("name"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<Reservation,String>("type"));
        colStatus.setCellValueFactory(new PropertyValueFactory<Reservation,String>("status"));

    }

    private void loadTableData() {
        RegistrationServiceImpl registrationService = (RegistrationServiceImpl) ServiceFactory.getService(ServiceTypes.RESERVATION);
        List<ReservationDTO> list = registrationService.getAll();

        ObservableList<TableModel> observableList = FXCollections.observableArrayList();

        for (ReservationDTO reservationDTO: list) {
            TableModel tableModel = new TableModel(
                    reservationDTO.getDate(),
                    reservationDTO.getRes_id(),
                    reservationDTO.getRoom().getRoom_type_id(),
                    reservationDTO.getStudent().getStudent_id(),
                    reservationDTO.getStudent().getName(),
                    reservationDTO.getRoom().getType(),
                    reservationDTO.getStatus());

            observableList.add(tableModel);
        }
        tblReservationDetails.setItems(observableList);
    }
}
