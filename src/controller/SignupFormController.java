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

import java.io.IOException;

public class SignupFormController {
    public AnchorPane container;
    public TextField txtEmail;
    public PasswordField txtPassword;
    public TextField txtFullName;

    private ApplicationUserBo bo = BoFactory.getInstance().getBo(BoType.APPLICATION_USER);

    public void LoginOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }

    public void registerOnAction(ActionEvent actionEvent) {
        try{
            boolean isSaved = bo.signup(
                    new ApplicationUserDto(
                            txtEmail.getText(),txtFullName.getText(),txtPassword.getText()
                    )
            );
            if(isSaved){
                new Alert(Alert.AlertType.INFORMATION,"Application User Saved!").show();
                setUi("LoginForm");
            }else{
                new Alert(Alert.AlertType.WARNING,"Try Again later..").show();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            new Alert(Alert.AlertType.ERROR,"Something went wrong!").show();
        }
    }

    private void setUi(String path) throws IOException {
        Stage stage = (Stage) container.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/"+path+".fxml")))
        );
    }

}
