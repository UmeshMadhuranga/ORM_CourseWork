package lk.ijse.D24_Hostel.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.D24_Hostel.dto.UserDTO;
import lk.ijse.D24_Hostel.service.impl.UserServiceImpl;
import lk.ijse.D24_Hostel.service.util.ServiceFactory;
import lk.ijse.D24_Hostel.service.util.ServiceTypes;

public class UserFormController {
    public JFXTextField txtUserName;
    public JFXTextField txtPassword;

    public void txtUserNameOnAction(ActionEvent actionEvent) {
        UserServiceImpl userService = (UserServiceImpl) ServiceFactory.getService(ServiceTypes.USER);

        UserDTO userDTO = userService.searchUser(txtUserName.getText());
        txtPassword.setText(userDTO.getPassword());
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        UserServiceImpl userService = (UserServiceImpl) ServiceFactory.getService(ServiceTypes.USER);

        boolean isAdded = userService.addUser(new UserDTO(txtUserName.getText(), txtPassword.getText()));
        if (isAdded) {
            new Alert(Alert.AlertType.CONFIRMATION, "Done..").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Error..").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        UserServiceImpl userService = (UserServiceImpl) ServiceFactory.getService(ServiceTypes.USER);

        boolean isUpdated = userService.updateUser(new UserDTO(txtUserName.getText(), txtPassword.getText()));
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "Done..").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Error..").show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        UserServiceImpl userService = (UserServiceImpl) ServiceFactory.getService(ServiceTypes.USER);

        boolean isDelete = userService.deleteUser(txtUserName.getText());
        if (isDelete) {
            new Alert(Alert.AlertType.CONFIRMATION, "Done..").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Error..").show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtUserName.clear();
        txtPassword.clear();
    }

    public void btnCloseOnAction(ActionEvent actionEvent) {
        RegisterFormController.stage.close();
    }
}
