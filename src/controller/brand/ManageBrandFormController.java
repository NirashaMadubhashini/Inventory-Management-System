package controller.brand;

import controller.brand.service.BrandService;
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
import model.Brand;
import util.TableBrandLoadEvent;
import view.tm.BrandTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManageBrandFormController extends BrandController implements BrandService, TableBrandLoadEvent {
    public AnchorPane ManageBrandFormContext;

    public TableView<BrandTm> tblManageBrand;
    public TableColumn colBrandId;
    public TableColumn colBrandCategoryName;
    public TableColumn colBrandName;
    public TableColumn colBrandDate;
    public TableColumn colBrandTime;
    public TableColumn colBrandUpdate;
    public TableColumn colBrandDelete;
    public TextField txtBrandSearch;


    public void initialize() {
        colBrandId.setCellValueFactory(new PropertyValueFactory<>("brandId"));
        colBrandName.setCellValueFactory(new PropertyValueFactory<>("brandName"));
        colBrandCategoryName.setCellValueFactory(new PropertyValueFactory<>("CategoryName"));
        colBrandDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colBrandTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colBrandUpdate.setCellValueFactory(new PropertyValueFactory<>("update"));
        colBrandDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));

        tblManageBrand.getColumns().setAll(colBrandId, colBrandName, colBrandCategoryName, colBrandDate,
                colBrandTime, colBrandUpdate, colBrandDelete);


        txtBrandSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                search(newValue);
            }
        });

        try {
            loadAllBrands(new BrandController().getAllBrands());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    private void loadAllBrands(ArrayList<Brand> brands) {
        try {
            ObservableList<BrandTm> tmObservableList = FXCollections.observableArrayList();


            for (Brand temp : brands
            ) {
                Button btnUpdate = new Button("Update");
                Button btnDelete = new Button("Delete");


                String categoryName = getCategoryNameByCategoryId(temp.getBrandCategoryId());

                tmObservableList.add(
                        new BrandTm(temp.getBrandId(), categoryName,
                                temp.getBrandName(), temp.getDate(),
                                temp.getTime(), btnUpdate, btnDelete)
                );


                btnDelete.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "Are you sure you want to delete this Category?", yes, no);
                    alert.setTitle("Conformation Alert");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.orElse(no) == yes) {

                        deleteBrand(temp);
                        brands.remove(temp);
                        loadAllBrands(brands);

                    } else {

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
            tblManageBrand.setItems(tmObservableList);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.setTitle("Error Alert");
        }
    }

    private void showUpdateForm(Brand table) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/UpdateBrandForm.fxml"));
            Parent parent = loader.load();
            UpdateBrandFormController controller = loader.<UpdateBrandFormController>getController();
            controller.txtUpdateBrandName.setText(table.getBrandName());
            controller.brandId = table.getBrandId();
            controller.setEvent(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteBrand(Brand temp) {
        try {
            if (new BrandController().deleteBrand(temp.getBrandName())) {

                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();

            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }




    public void BrandSearchOnAction(ActionEvent actionEvent) {
        search(txtBrandSearch.getText());
    }


    public String getCategoryNameByCategoryId(int id) {
        String categoryNameByID = "";
        try {
            categoryNameByID = new BrandFormController().getCategoryNameByID(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return categoryNameByID;
    }

    private void search(String value) {
        try {
            List<Brand> brands = new BrandFormController().searchBrand(value);
            loadAllBrands((ArrayList<Brand>) brands);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void loadData() {
        try {
            loadAllBrands(new BrandController().getAllBrands());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void BackBrandOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) ManageBrandFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashBoardForm.fxml"))));
    }
}
