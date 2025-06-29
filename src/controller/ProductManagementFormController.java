package controller;

import bo.BoFactory;
import bo.BoType;
import bo.custom.ProductBo;
import dto.CustomerDto;
import dto.ProductDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.CustomerTM;
import view.tm.ProductTm;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    private String searchText="";
    ProductBo productBo = BoFactory.getInstance().getBo(BoType.PRODUCT);

    public void initialize(){
        txtId.setEditable(false);
        txtId.setText(UUID.randomUUID().toString());

        /*=================*/
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(oldValue);
            if(newValue!=null && !newValue.isEmpty()){
                searchText = newValue;
                loadAllProducts();
            }
        });

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQTYOnHand.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("bar"));
        //=====================

        loadAllProducts();

    }

    public void saveProductOnAction(ActionEvent actionEvent) {
        if(btnSave.getText().equals("Save Product")) {
            try{
                boolean isSaved = productBo.save(
                        new ProductDto(
                                txtId.getText(),
                                Integer.parseInt(txtQTYOnHand.getText()),
                                Double.parseDouble(txtUnitPrice.getText()),
                                txtName.getText()
                        )
                );
                if(isSaved){
                    new Alert(Alert.AlertType.INFORMATION,"Product Saved").show();
                    loadAllProducts();
                }else{
                    new Alert(Alert.AlertType.WARNING,"Try Again").show();
                }
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR,"Try Again").show();
            }
        }else{
            try{
                boolean isUpdated = productBo.update(
                        new ProductDto(
                                txtId.getText(),
                                Integer.parseInt(txtQTYOnHand.getText()),
                                Double.parseDouble(txtUnitPrice.getText()),
                                txtName.getText()
                        )
                );
                if(isUpdated){
                    new Alert(Alert.AlertType.INFORMATION,"Product Updated").show();
                    loadAllProducts();
                }else{
                    new Alert(Alert.AlertType.WARNING,"Try Again").show();
                }
            }catch (Exception e){
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR,"Try Again").show();
            }
        }
    }

    public void newProductOnAction(ActionEvent actionEvent) {
        clear();
    }
    private void clear(){
        txtId.clear();
        txtName.clear();
        txtUnitPrice.clear();
        txtQTYOnHand.clear();
        btnSave.setText("Save Product");
        txtId.setText(UUID.randomUUID().toString());
    }
    private void loadAllProducts() {
        ObservableList<ProductTm> obList = FXCollections.observableArrayList();
        try{
            List<ProductDto> searchData = productBo.search(searchText);
            for (ProductDto dto : searchData) {
                Button deleteButton = new Button("Delete");
                Button updateButton = new Button("Update");
                ButtonBar buttonBar = new ButtonBar();
                buttonBar.getButtons().addAll(deleteButton,updateButton);
                ProductTm tm = new ProductTm(
                        dto.getId(),
                        dto.getDescription(),
                        dto.getQty(),
                        dto.getUnitPrice(),
                        buttonBar
                );

                updateButton.setOnAction(event -> {
                    txtId.setText(tm.getId());
                    txtName.setText(tm.getDescription());
                    txtQTYOnHand.setText(String.valueOf(tm.getQty()));
                    txtUnitPrice.setText(String.valueOf(tm.getUnitPrice()));
                    btnSave.setText("Update Product");
                });

                deleteButton.setOnAction((e)->{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to delete this product?",ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if(buttonType.get()==ButtonType.YES){
                        try{
                            boolean delete = productBo.delete(tm.getId());
                            if(delete){
                                new Alert(Alert.AlertType.INFORMATION, "Deleted!");
                                loadAllProducts();
                            }

                        }catch (Exception ex){

                        }
                    }
                });

                obList.add(tm);
            }
            table.setItems(obList);
        }catch (Exception e){
            System.out.println(e);
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
