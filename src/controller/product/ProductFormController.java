package controller.product;

import com.jfoenix.controls.JFXComboBox;
import controller.brand.BrandController;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Product;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


public class ProductFormController extends ProductController{
    public AnchorPane ProductFormContext;

    public TextField txtProductName;
    public TextField txtProductSerialNumber;
    public JFXComboBox<String> cmbProductBrandName;
    public TextField txtProductPrice;
    public Button btnAddProduct;
    public TextField txtProductQty;


    public void initialize() throws SQLException, ClassNotFoundException {
        loadBrandNames();
        btnAddProduct.setDisable(true);
    }

    private void loadBrandNames() throws SQLException, ClassNotFoundException {
        List<String>brandNames=new BrandController().getBrandNames();
        cmbProductBrandName.getItems().addAll(brandNames);
    }

    private boolean isExists(Product p) throws SQLException, ClassNotFoundException {
        ArrayList<Product>allProducts=new ProductFormController().getAllProducts();
        for (int i=0;i<allProducts.size();i++){
            if (p.getProductName().equals(allProducts.get(i).getProductName())){
                return true;
            }
        }
        return false;
    }

    public void AddProductOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (!txtProductSerialNumber.getText().trim().isEmpty() &&! cmbProductBrandName.getValue().trim().isEmpty()
        &&! txtProductName.getText().trim().isEmpty() &&! txtProductQty.getText().trim().isEmpty() &&!
                txtProductPrice.getText().trim().isEmpty()){

            Date date2=new Date();
            SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
            String date=f.format(date2);

            LocalTime currentTime=LocalTime.now();
            String time="";
            time += currentTime.getHour()+" : " + currentTime.getMinute()+
                    " : " + currentTime.getSecond();

            cmbProductBrandName.getValue();

            Product p=new Product(
                    txtProductSerialNumber.getText(),
                    txtProductName.getText(),
                    Integer.parseInt(txtProductQty.getText()),
                    Double.parseDouble(txtProductPrice.getText()),
                    date,time
            );
            if (!isExists(p)){
               try {
                   if (new ProductFormController().saveProduct(cmbProductBrandName.getValue(),p)){
                       new Alert(Alert.AlertType.CONFIRMATION,"Saved..").show();

                   }else{
                       new Alert(Alert.AlertType.WARNING,"Try Again..").show();

                   }

                   Stage stage=(Stage) btnAddProduct.getScene().getWindow();
                   stage.close();

               }catch (Exception e){
                   new Alert(Alert.AlertType.WARNING,e.getMessage());
                   e.printStackTrace();
               }
            }else{
                new Alert(Alert.AlertType.WARNING,"Product AlReady Exits").show();

            }
        }
    }



    public void ProductName_keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {

            String regEx = "^[A-z 0-9]{0,}$";
            String typeText = txtProductName.getText();
            Pattern compile = Pattern.compile(regEx);
            boolean matches = compile.matcher(typeText).matches();


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtProductName.setStyle("-fx-border-color: dodgerblue");
                    txtProductPrice.requestFocus();
                } else {
                    txtProductName.setStyle("-fx-border-color: red");
                }
            }
        }
    }

    public void SerialNumber_keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {

            String regEx = "^[A-z 0-9]{0,}$";
            String typeText = txtProductSerialNumber.getText();
            Pattern compile = Pattern.compile(regEx);
            boolean matches = compile.matcher(typeText).matches();


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtProductSerialNumber.setStyle("-fx-border-color: dodgerblue");
                    cmbProductBrandName.requestFocus();
                } else {
                    txtProductSerialNumber.setStyle("-fx-border-color: red");
                }
            }
        }
    }

    public void Qty_keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {

            String regEx = "^[0-9]{0,}$";
            String typeText = txtProductQty.getText();
            Pattern compile = Pattern.compile(regEx);
            boolean matches = compile.matcher(typeText).matches();


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtProductQty.setStyle("-fx-border-color: dodgerblue");
                    btnAddProduct.setDisable(false);
                } else {
                    txtProductQty.setStyle("-fx-border-color: red");
                    btnAddProduct.setDisable(true);
                }
            }
        }
    }

    public void Price_keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {

            String regEx = "^[0-9]{0,}$";
            String typeText = txtProductPrice.getText();
            Pattern compile = Pattern.compile(regEx);
            boolean matches = compile.matcher(typeText).matches();


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtProductPrice.setStyle("-fx-border-color: dodgerblue");
                    txtProductQty.requestFocus();
                } else {
                    txtProductPrice.setStyle("-fx-border-color: red");
                }
            }
        }
    }
}
