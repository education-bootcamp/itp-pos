package controller;

import bo.BoFactory;
import bo.BoType;
import bo.custom.ApplicationUserBo;
import dto.ApplicationUserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import util.PasswordManager;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane container;
    public TextField txtEmail;
    public PasswordField txtPassword;

    private ApplicationUserBo applicationUserBo = BoFactory.getInstance().getBo(BoType.APPLICATION_USER);

    public void accessDashboardOnAction(ActionEvent actionEvent) {
        try {
            ApplicationUserDto selectedUser = applicationUserBo.login(txtEmail.getText());
            if (selectedUser == null) {
                new Alert(Alert.AlertType.WARNING, "user name not found").show();
                return;
            }
            if(PasswordManager.decryptPassword(
                    selectedUser.getPassword(),
                    txtPassword.getText()
            )){
                setUi("DashboardForm");
            }else{
                new Alert(Alert.AlertType.WARNING, "check your password").show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi("SignupForm");
    }

    private void setUi(String path) throws IOException {
        Stage stage = (Stage) container.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/" + path + ".fxml")))
        );
    }
}
