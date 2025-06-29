package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {
    public AnchorPane container;

    public void openCustomerForm(MouseEvent mouseEvent) throws IOException {
        setUi("CustomerManagementForm");
    }


    private void setUi(String path) throws IOException {
        Stage stage = (Stage) container.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/" + path + ".fxml")))
        );
    }

    public void openProductManagementOnAction(MouseEvent mouseEvent) throws IOException {
        setUi("ProductManagementForm");
    }
}
