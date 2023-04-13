package lk.ijse.D24_Hostel.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.D24_Hostel.dto.UserDTO;
import lk.ijse.D24_Hostel.service.impl.UserServiceImpl;
import lk.ijse.D24_Hostel.service.util.ServiceFactory;
import lk.ijse.D24_Hostel.service.util.ServiceTypes;

public class SignUpFormController {
    public TextField txtUserName;
    public PasswordField txtPassword;

    public void btnSignUp(ActionEvent actionEvent) {
        UserServiceImpl userServiceDao = (UserServiceImpl) ServiceFactory.getService(ServiceTypes.USER);

        String name = txtUserName.getText();
        String password = txtPassword.getText();

        UserDTO userDTO = new UserDTO(name, password);

        boolean b = userServiceDao.addUser(userDTO);

        if (b){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "SingUp SuccessFully!");
            alert.show();
            LoginFormController.stage2.close();
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "SingUp Error");
            txtUserName.clear();
            txtPassword.clear();
            alert.show();
        }

    }
}
