package lk.ijse.D24_Hostel.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes routes, AnchorPane pane) throws IOException {
        Navigation.pane = pane;
        Navigation.pane.getChildren().clear();

        switch (routes) {
            case STUDENT:
                initUI("StudentForm.fxml");
                break;

            case ROOMS:
                initUI("RoomForm.fxml");
                break;

            case REGISTRATION:
                initUI("RegisterForm.fxml");
                break;

            case LOGIN:
                initUI("LoginForm.fxml");
                break;

            case REGISTRATIONDETAILS:
                initUI("ReservationDetailsForm.fxml");
                break;

            default:
                Alert alert = new Alert(Alert.AlertType.ERROR, "wrong");
                alert.show();
        }
    }

    public static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader
                .load(Navigation.class.getResource("/lk/ijse/D24_Hostel/view/" + location))
        );
    }
}



