package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane container;
    public TextField txtEmail;
    public PasswordField txtPassword;

    public void accessDashboardOnAction(ActionEvent actionEvent) {
    }

    public void createAnAccountOnAction(ActionEvent actionEvent) throws IOException {
       setUi("SignupForm");
    }
    private void setUi(String path) throws IOException {
        Stage stage = (Stage) container.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/"+path+".fxml")))
        );
    }
}
