package controller;

import bo.BoFactory;
import bo.BoType;
import bo.custom.CustomerBo;
import dto.CustomerDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import view.CustomerTM;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CustomerManagementFormController {

    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;
    public Button btnSave;
    public TableView<CustomerTM> tblCustomers;
    public TableColumn colName;
    public TableColumn colId;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public TableColumn colOption;
    public TextField txtSearch;

    private String searchText="";

    private CustomerBo customerBo= BoFactory.getInstance().getBo(BoType.CUSTOMER);

    public void initialize(){
        txtId.setEditable(false);
        txtId.setText(UUID.randomUUID().toString());

        /*=================*/
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(oldValue);
            if(newValue!=null && !newValue.isEmpty()){
                searchText = newValue;
                loadAllCustomers();
            }
        });

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("buttonBar"));
        //=====================

        loadAllCustomers();

    }

    public void saveCustomerOnAction(ActionEvent actionEvent) {
        if (btnSave.getText().equals("Save Customer")){
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
            try{
                boolean isUpdated = customerBo.update(
                        new CustomerDto(
                                txtId.getText(),txtName.getText(),
                                txtAddress.getText(),
                                Double.parseDouble(txtSalary.getText())
                        )
                );
                if(isUpdated){
                    new Alert(Alert.AlertType.INFORMATION,"Customer Updated").show();
                    loadAllCustomers();
                }else{
                    new Alert(Alert.AlertType.WARNING,"Try Again").show();
                }
            }catch (Exception e){
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR,"Try Again").show();
            }
        }
    }

    private void loadAllCustomers() {
        ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
        try{
            List<CustomerDto> searchData = customerBo.search(searchText);
            for (CustomerDto customerDto : searchData) {
                Button deleteButton = new Button("Delete");
                Button updateButton = new Button("Update");
                ButtonBar buttonBar = new ButtonBar();
                buttonBar.getButtons().addAll(deleteButton,updateButton);
                CustomerTM tm = new CustomerTM(
                        customerDto.getId(),
                        customerDto.getName(),
                        customerDto.getAddress(),
                        customerDto.getSalary(),
                        buttonBar
                );

                updateButton.setOnAction(event -> {
                    txtId.setText(tm.getId());
                    txtName.setText(tm.getName());
                    txtAddress.setText(tm.getAddress());
                    txtSalary.setText(String.valueOf(tm.getSalary()));
                    btnSave.setText("Update Customer");
                });

                deleteButton.setOnAction((e)->{
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to delete this customer?",ButtonType.YES,ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if(buttonType.get()==ButtonType.YES){
                       try{
                           boolean delete = customerBo.delete(tm.getId());
                           if(delete){
                               new Alert(Alert.AlertType.INFORMATION, "Deleted!");
                               loadAllCustomers();
                           }

                       }catch (Exception ex){

                       }
                    }
                });

                obList.add(tm);
            }
            tblCustomers.setItems(obList);
        }catch (Exception e){

        }
    }

    public void newCustomerOnAction(ActionEvent actionEvent) {
        clear();
    }
    private void clear(){
        txtId.clear();
        txtName.clear();
        txtSalary.clear();
        txtAddress.clear();
        btnSave.setText("Save Customer");
    }
}
