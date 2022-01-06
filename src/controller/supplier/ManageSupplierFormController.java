package controller.supplier;


import controller.supplier.service.SupplierService;
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

import model.Supplier;
import util.TableSupplierLoadEvent;
import view.tm.SupplierTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManageSupplierFormController extends SupplierController implements SupplierService , TableSupplierLoadEvent {
    public AnchorPane ManageSupplierFormContext;

    public TableView<SupplierTm> tblManageSupplier;

    public TableColumn colSupplierNIC;
    public TableColumn colSupplierName;
    public TableColumn colSupplierEmail;
    public TableColumn colSupplierDescription;
    public TableColumn colSupplierUpdate;
    public TableColumn colSupplierDelete;
    public TextField txtSupplierSearch;

    public void initialize() throws SQLException, ClassNotFoundException {
        colSupplierNIC.setCellValueFactory(new PropertyValueFactory<>("SupplierNIC"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("SupplierName"));
        colSupplierEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colSupplierDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colSupplierUpdate.setCellValueFactory(new PropertyValueFactory<>("Update"));
        colSupplierDelete.setCellValueFactory(new PropertyValueFactory<>("Delete"));

        tblManageSupplier.getColumns().setAll(colSupplierNIC, colSupplierName, colSupplierEmail, colSupplierDescription,
                colSupplierUpdate, colSupplierDelete);


        txtSupplierSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                search(newValue);
            }
        });

        try {
            loadAllSuppliers(new SupplierController().getAllSuppliers());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



    private void loadAllSuppliers(ArrayList<Supplier> suppliers) {
        try {
            ObservableList<SupplierTm> tmObservableList = FXCollections.observableArrayList();

            for (Supplier temp : suppliers
            ) {
                Button btnUpdate = new Button("Update");
                Button btnDelete = new Button("Delete");
                tmObservableList.add(

                        new SupplierTm(temp.getSupplierNIC(), temp.getSupplierName(), temp.getEmail(), temp.getDescription(),
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

                        deleteSupplier(temp);
                        suppliers.remove(temp);
                        loadAllSuppliers(suppliers);
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
            tblManageSupplier.setItems(tmObservableList);


        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.setTitle("Error Alert");
        }
    }

    private void showUpdateForm(Supplier table) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/UpdateSupplierForm.fxml"));
            Parent parent = loader.load();
            UpdateSupplierFormController controller = loader.<UpdateSupplierFormController>getController();
            controller.txtUpdateSupplierName.setText(table.getSupplierName());
            controller.txtUpdateSupplierEmail.setText(table.getEmail());
            controller.txtUpdateSupplierDescription.setText(table.getDescription());
            controller.supplierNIC = table.getSupplierNIC();
            controller.setEvent(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
private void deleteSupplier(Supplier temp){
    try {
       if(new SupplierController().deleteSupplier(temp.getSupplierName())){

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

    public void SupplierSearchOnAction(ActionEvent actionEvent) {
        search(txtSupplierSearch.getText());
    }

    private void search(String value) {
        try {
            List<Supplier> suppliers= new SupplierFormController().searchSupplier(value);
            loadAllSuppliers((ArrayList<Supplier>) suppliers);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void loadData() {
        try {
            loadAllSuppliers(new SupplierController().getAllSuppliers());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




    public void BackToSupplierOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) ManageSupplierFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashBoardForm.fxml"))));
    }


}
