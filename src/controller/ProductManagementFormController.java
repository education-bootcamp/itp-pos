package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import view.tm.ProductTm;

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

    public void saveProductOnAction(ActionEvent actionEvent) {
    }

    public void newProductOnAction(ActionEvent actionEvent) {
    }

    public void backToHomeOnAction(ActionEvent actionEvent) {
    }
}
