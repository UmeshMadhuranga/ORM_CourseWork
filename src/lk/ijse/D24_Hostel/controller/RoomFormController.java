package lk.ijse.D24_Hostel.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.D24_Hostel.dto.RoomDTO;
import lk.ijse.D24_Hostel.dto.StudentDTO;
import lk.ijse.D24_Hostel.entity.Room;
import lk.ijse.D24_Hostel.service.impl.RoomServiceImpl;
import lk.ijse.D24_Hostel.service.util.ServiceFactory;
import lk.ijse.D24_Hostel.service.util.ServiceTypes;
import lk.ijse.D24_Hostel.service.util.SuperService;
import lk.ijse.D24_Hostel.util.Navigation;
import lk.ijse.D24_Hostel.util.Routes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class RoomFormController {
    public AnchorPane pane;

    public TableView tblRooms;
    public TableColumn colRoomId;
    public TableColumn colType;
    public TableColumn colKeyMoney;
    public TableColumn colQty;

    public Label lblDate;

    public JFXTextField txtRoomID;
    public JFXTextField txtType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtQty;

    public RoomServiceImpl roomService = (RoomServiceImpl) ServiceFactory.getService(ServiceTypes.ROOMS);

    public void initialize() {
        loadRooms();
        lblDate.setText(String.valueOf(LocalDate.now()));

        colRoomId.setCellValueFactory(new PropertyValueFactory<Room,String>("room_type_id"));
        colType.setCellValueFactory(new PropertyValueFactory<Room,String>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<Room,String>("key_money"));
        colQty.setCellValueFactory(new PropertyValueFactory<Room,Integer>("qty"));
    }

    private void loadRooms() {
        ObservableList<RoomDTO> observableList = FXCollections.observableArrayList();

        List<RoomDTO> allRooms = roomService.getAllRooms();
        for (RoomDTO roomDTO: allRooms) {
            observableList.add(roomDTO);
        }
        tblRooms.setItems(observableList);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        RoomDTO roomDTO = new RoomDTO(
                txtRoomID.getText(),
                txtType.getText(),
                Double.parseDouble(txtKeyMoney.getText()),
                Integer.parseInt(txtQty.getText())
        );

        boolean isAdded = roomService.addRoom(roomDTO);
        if (isAdded) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Done.");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error..!");
            alert.show();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        RoomDTO roomDTO = new RoomDTO(
                txtRoomID.getText(),
                txtType.getText(),
                Double.parseDouble(txtKeyMoney.getText()),
                Integer.parseInt(txtQty.getText())
        );

        boolean isUpdated = roomService.updateRoom(roomDTO);
        if (isUpdated) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Done.");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error..!");
            alert.show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

        boolean isDeleted = roomService.deleteRoom(txtRoomID.getText());
        if (isDeleted) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Done.");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error..!");
            alert.show();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {

        RoomDTO roomDTO = roomService.searchRoom(txtRoomID.getText());

        txtType.setText(roomDTO.getType());
        txtKeyMoney.setText(String.valueOf(roomDTO.getKey_money()));
        txtQty.setText(String.valueOf(roomDTO.getQty()));

    }

    public void btnClearOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ROOMS, pane);
    }

    public void imgSettingOnMouseClicked(MouseEvent mouseEvent) {
    }

    public void txtRoomIDOnAction(ActionEvent actionEvent) {
        btnSearchOnAction(actionEvent);
    }

    public void tblRoomsOnMouseClicked(MouseEvent mouseEvent) {
        RoomDTO roomDTO = (RoomDTO) tblRooms.getSelectionModel().getSelectedItem();

        txtRoomID.setText(roomDTO.getRoom_type_id());
        txtType.setText(roomDTO.getType());
        txtKeyMoney.setText(String.valueOf(roomDTO.getKey_money()));
        txtQty.setText(String.valueOf(roomDTO.getQty()));
    }
}
