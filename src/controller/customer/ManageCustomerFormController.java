package controller.customer;

import controller.customer.service.CustomerService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Customer;
import util.TableCustomerLoadEvent;
import view.tm.CustomerTm;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManageCustomerFormController extends CustomerController implements CustomerService , TableCustomerLoadEvent {
    public AnchorPane CustomerFormContext;

    public TableView<CustomerTm> tblManageCustomer;

    public TableColumn colCustomerNic;
    public TableColumn colCustomerName;
    public TableColumn colCustomerTp;
    public TableColumn colCustomerUpdate;
    public TableColumn colCustomerDelete;
    public TextField txtCustomerSearch;


    public void initialize(){

        colCustomerNic.setCellValueFactory(new PropertyValueFactory<>("customerNIC"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCustomerTp.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        colCustomerUpdate.setCellValueFactory(new PropertyValueFactory<>("update"));
        colCustomerDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));


        tblManageCustomer.getColumns().setAll(colCustomerNic,colCustomerName,colCustomerTp,colCustomerUpdate,colCustomerDelete);

        txtCustomerSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    search(newValue);
            }

        });

        try {
            loadAllCustomers(new CustomerController().getAllCustomer());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    private void loadAllCustomers(ArrayList<Customer> customers){
        try {
            ObservableList<CustomerTm> tmObservableList = FXCollections.observableArrayList();

            for (Customer temp : customers
            ) {
                Button btnUpdate = new Button("Update");
                Button btnDelete = new Button("Delete");
                tmObservableList.add(

                        new CustomerTm(temp.getCustomerNIC(), temp.getName(), temp.getMobileNumber(),
                                btnUpdate, btnDelete)
                );

                btnDelete.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "Are you sure you want to delete this Supplier?", yes, no);
                    alert.setTitle("Conformation Alert");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.orElse(no)==yes){

                        deleteCustomer(temp);
                        customers.remove(temp);
                        loadAllCustomers(customers);
                    }

                });

                btnUpdate.setOnAction((e) -> {
                    try {
                        showUpdateForm(temp);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                });
            }
            tblManageCustomer.setItems(tmObservableList);


        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.setTitle("Error Alert");
        }
    }

    private void showUpdateForm(Customer table) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/UpdateCustomerForm.fxml"));
            Parent parent = loader.load();
            UpdateCustomerFormController controller = loader.<UpdateCustomerFormController>getController();
            controller.txtUpdateCustomerName.setText(table.getName());
            controller.txtUpdateCustomerMobileNumber.setText(table.getMobileNumber());
            controller.customerNIC = table.getCustomerNIC();
            controller.setEvent(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteCustomer(Customer temp){
        try {
            if(new CustomerController().deleteCustomer(temp.getName())){

                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();

            }else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } {

        }
    }

    public void CustomerSearchOnAction(ActionEvent actionEvent) {
    }

    private void search(String value) {
        try {
            List<Customer> customers= new CustomerFormController().searchCustomer(value);
            loadAllCustomers((ArrayList<Customer>) customers);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }


    @Override
    public void loadData() {
        try {
            loadAllCustomers(new CustomerController().getAllCustomer());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) CustomerFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashBoardForm.fxml"))));
    }

}
