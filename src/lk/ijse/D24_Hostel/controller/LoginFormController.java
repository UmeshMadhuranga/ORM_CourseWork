package lk.ijse.D24_Hostel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.D24_Hostel.dto.UserDTO;
import lk.ijse.D24_Hostel.service.impl.UserServiceImpl;
import lk.ijse.D24_Hostel.service.util.ServiceFactory;
import lk.ijse.D24_Hostel.service.util.ServiceTypes;
import lk.ijse.D24_Hostel.util.Navigation;
import lk.ijse.D24_Hostel.util.Routes;

import java.io.IOException;
import java.util.List;

public class LoginFormController {
    public TextField txtUserName;
    public TextField txtPassword;
    public ImageView openEye;
    public ImageView closeEye;
    public TextField txtPassword2;

    public static Stage stage2;
    public AnchorPane pane;

    public void txtUserNameOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void btnLogin(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equals("") || txtPassword.getText().equals("")) {
            new Alert(Alert.AlertType.ERROR, "Please Enter User name And Password.!").show();
            return;
        }

        UserServiceImpl userService = (UserServiceImpl) ServiceFactory.getService(ServiceTypes.USER);

        List<UserDTO> list = userService.getAll();

        for (UserDTO userDTO : list) {
            if (userDTO.getUserName().equalsIgnoreCase(txtUserName.getText()) && userDTO.getPassword()
                    .equals(txtPassword.getText()) || userDTO.getPassword().equals(txtPassword2.getText())) {
                Navigation.navigate(Routes.REGISTRATION, pane);
                return;
            } else {
                new Alert(Alert.AlertType.ERROR, "Please enter correct user name and password.!").show();
                return;
            }
        }
    }

    public void btnSignUp(ActionEvent actionEvent) throws IOException {
        stage2 = new Stage();
        stage2.setScene(new Scene(FXMLLoader.load(getClass()
                .getResource("/lk/ijse/D24_Hostel/view/SignUpForm.fxml"))));
        stage2.setTitle("Add new user");
        stage2.show();
    }

    public void closeEyeOnMouseClicked(MouseEvent mouseEvent) {
        txtPassword.setVisible(false);
        closeEye.setVisible(false);

        txtPassword2.setVisible(true);
        openEye.setVisible(true);

        txtPassword2.setText(txtPassword.getText());
        txtPassword2.requestFocus();
    }

    public void openEyeOnMouseClicked(MouseEvent mouseEvent) {
        txtPassword.setVisible(true);
        closeEye.setVisible(true);

        txtPassword2.setVisible(false);
        openEye.setVisible(false);

        txtPassword.requestFocus();
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) throws IOException {
        btnLogin(actionEvent);
    }

    public void txtPassword2OnAction(ActionEvent actionEvent) throws IOException {
        btnLogin(actionEvent);
    }
}
