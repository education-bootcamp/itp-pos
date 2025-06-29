package controller;

import dto.CustomerDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.ProductTm;

import java.io.IOException;

public class ProductManagementFormController {
    public AnchorPane container;
    public TextField txtId;
    public TextField txtName;
    public TextField txtUnitPrice;
    public TextField txtQTYOnHand;
    public TextField txtSearch;
    public TableView<ProductTm> table;
    public TableColumn<ProductTm, String> colDescription;
    public TableColumn<ProductTm, String> colId;
    public TableColumn<ProductTm, Integer> colUnitPrice;
    public TableColumn<ProductTm, ButtonBar> colOption;
    public TableColumn<ProductTm, Double> colQTYOnHand;
    public Button btnSave;

    public void saveProductOnAction(ActionEvent actionEvent) {
    }

    public void newProductOnAction(ActionEvent actionEvent) {
        if(btnSave.getText().equals("Save Product")) {
            try{
                boolean isSaved = customerBo.save(
                        new CustomerDto(
                                txtId.getText(),txtName.getText(),
                                txtAddress.getText(),
                                Double.parseDouble(txtSalary.getText())
                        )
                );
                if(isSaved){
                    new Alert(Alert.AlertType.INFORMATION,"Customer Saved").show();
                    loadAllCustomers();
                }else{
                    new Alert(Alert.AlertType.WARNING,"Try Again").show();
                }
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR,"Try Again").show();
            }
        }else{
            //update
        }
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }
    private void setUi(String path) throws IOException {
        Stage stage = (Stage) container.getScene().getWindow();
        stage.setScene(
                new Scene(FXMLLoader.load(getClass().getResource("../view/" + path + ".fxml")))
        );
    }
}
