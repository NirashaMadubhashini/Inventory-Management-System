package controller.product;

import controller.product.service.ProductService;
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
import model.Product;
import util.TableProductLoadEvent;
import view.tm.ProductTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManageProductFormController extends ProductController implements ProductService, TableProductLoadEvent {
    public AnchorPane ManageProductFormContext;

    public TableView<ProductTm> tblManageProduct;

    public TableColumn colProductId;
    public TableColumn colProductSerialNumber;
    public TableColumn colProductBrandName;
    public TableColumn colProductName;
    public TableColumn colProductQty;
    public TableColumn colProductPrice;
    public TableColumn colProductDate;
    public TableColumn colProductTime;
    public TableColumn colProductUpdate;
    public TableColumn colProductDelete;


    public TextField txtProductSearch;



    public void initialize() {
        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colProductSerialNumber.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colProductBrandName.setCellValueFactory(new PropertyValueFactory<>("brandName"));
        colProductQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colProductDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colProductTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colProductUpdate.setCellValueFactory(new PropertyValueFactory<>("update"));
        colProductDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));

        tblManageProduct.getColumns().setAll(colProductId, colProductSerialNumber, colProductName, colProductBrandName,
             colProductQty,colProductPrice, colProductDate, colProductTime, colProductUpdate, colProductDelete);

        txtProductSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                search(newValue);
            }
        });

        try {
            loadAllProducts(new ProductController().getAllProducts());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    private void loadAllProducts(ArrayList<Product> products) {
        try {
            ObservableList<ProductTm> tmObservableList = FXCollections.observableArrayList();


            for (Product temp : products
            ) {
                Button btnUpdate = new Button("Update");
                Button btnDelete = new Button("Delete");

                String brandName = getBrandNameByBrandId(temp.getBrandId());

                tmObservableList.add(
                        new ProductTm(temp.getProductId(), temp.getSerialNumber(),
                                temp.getProductName(),brandName,temp.getQty(),temp.getPrice(), temp.getDate(), temp.getTime(),
                                btnUpdate, btnDelete)
                );

                btnDelete.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "Are you sure you want to delete this Category?", yes, no);
                    alert.setTitle("Conformation Alert");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.orElse(no) == yes) {
                        deleteProduct(temp);
                        products.remove(temp);
                        loadAllProducts(products);
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
            tblManageProduct.setItems(tmObservableList);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.setTitle("Error Alert");
        }
    }


    private void showUpdateForm(Product table) {

        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/UpdateProductForm.fxml"));
            Parent parent = loader.load();
            UpdateProductFormController controller = loader.<UpdateProductFormController>getController();
            controller.txtUpdateProductName.setText(table.getProductName());
            controller.txtUpdateProductQty.setText(String.valueOf(table.getQty()));
            controller.txtUpdateProductPrice.setText(String.valueOf(table.getPrice()));
            controller.productId = table.getProductId();
            controller.setEvent(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void ProductSearchOnAction(ActionEvent actionEvent) {
        search(txtProductSearch.getText());
    }


    private void search(String value) {
        try {
            List<Product>products = new ProductFormController().searchProduct(value);
            loadAllProducts((ArrayList<Product>) products);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    private String getBrandNameByBrandId(int id) {
        String brandNameByID = "";

        try {
            brandNameByID = new ProductController().getBrandNameByID(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return brandNameByID;
    }


    private void deleteProduct(Product temp) {
        try {
            if (new ProductController().deleteProduct(temp.getProductName())){

                new Alert(Alert.AlertType.CONFIRMATION,"Deleted").show();

            }else{

                new Alert(Alert.AlertType.WARNING,"Try Again").show();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void loadData() {
        try {
            loadAllProducts(new ProductController().getAllProducts());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void BackToProductOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) ManageProductFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashBoardForm.fxml"))));
    }
}
