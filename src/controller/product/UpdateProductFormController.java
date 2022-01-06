package controller.product;

import controller.product.service.ProductService;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Product;
import util.TableProductLoadEvent;

import java.sql.SQLException;

public class UpdateProductFormController extends ProductController implements ProductService {
    public AnchorPane UpdateProductFormContext;

    public int productId;

    public TextField txtUpdateProductName;
    public TextField txtUpdateProductPrice;

    public Button btnUpdateProduct;
    public TextField txtUpdateProductQty;

    private TableProductLoadEvent event;


    public void UpdateProductOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Product p=new Product(
                productId,
                txtUpdateProductName.getText(),
                Integer.parseInt(txtUpdateProductQty.getText()),
                Double.parseDouble(txtUpdateProductPrice.getText())
        );
        try {
            if (new ProductController().updateProduct(p)){
                new Alert(Alert.AlertType.CONFIRMATION,"Updated...").show();

                Stage stage=(Stage)btnUpdateProduct.getScene().getWindow();
                stage.close();

                event.loadData();
                
            }else {
                new Alert(Alert.AlertType.WARNING,"Try Again").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }




    public void setEvent(TableProductLoadEvent event){
        this.event=event;
    }

    public void MoveProductPrice(ActionEvent actionEvent) {
        txtUpdateProductPrice.requestFocus();
    }

    public void MoveProductQty(ActionEvent actionEvent) {
        txtUpdateProductQty.requestFocus();
    }
}
