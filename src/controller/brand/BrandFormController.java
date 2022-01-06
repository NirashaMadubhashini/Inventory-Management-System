package controller.brand;

import com.jfoenix.controls.JFXComboBox;
import controller.category.CategoryController;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Brand;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class BrandFormController extends BrandController{
    public AnchorPane BrandFormContext;
    public TextField txtBrandName;
    public JFXComboBox<String> cmbBrandCategoryName;

    public Button btnAddBrand;

    public void initialize() throws IOException, SQLException, ClassNotFoundException {

        loadCategoryNames();
        btnAddBrand.setDisable(true);

    }




    private boolean isExists(Brand b) throws SQLException, ClassNotFoundException {

        ArrayList<Brand> allBrands = new BrandFormController().getAllBrands();

        for (int i = 0; i < allBrands.size(); i++) {
            if (b.getBrandName().equals(allBrands.get(i).getBrandName())) {
                return true;
            }
        }
        return false;
    }

    private void loadCategoryNames() throws SQLException, ClassNotFoundException {
        List<String> categoryNames = new CategoryController()
                .getCategoryNames();
        cmbBrandCategoryName.getItems().addAll(categoryNames);
    }


    public void AddBrandOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (!cmbBrandCategoryName.getValue().trim().isEmpty() && !txtBrandName.getText().trim().isEmpty()) {


            Date date1 = new Date();
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String date = f.format(date1);

            LocalTime currentTime = LocalTime.now();
            String time = "";
            time += currentTime.getHour() + " : " + currentTime.getMinute() +
                    " : " + currentTime.getSecond();


            cmbBrandCategoryName.getValue();

            Brand b = new Brand(
                     txtBrandName.getText(),
                    date, time
            );
            if (!isExists(b)) {
                try {
                    if (new BrandFormController().saveBrand( cmbBrandCategoryName.getValue(),b)) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();

                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                    }

                    Stage stage = (Stage) btnAddBrand.getScene().getWindow();
                    stage.close();

                } catch (Exception e) {
                    new Alert(Alert.AlertType.WARNING, e.getMessage());
                    e.printStackTrace();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Brand Already Exits").show();
            }

        } else {
            new Alert(Alert.AlertType.WARNING, "Cannot Be Empty Try Again").show();
            cmbBrandCategoryName.setStyle("-fx-border-color: red");
            txtBrandName.setStyle("-fx-border-color: red");
        }


        }


        public void MoveBrandName (ActionEvent actionEvent){
        }

    public void BrandName_keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {

            String regEx = "^[A-z 0-9]{0,}$";
            String typeText = txtBrandName.getText();
            Pattern compile = Pattern.compile(regEx);
            boolean matches = compile.matcher(typeText).matches();


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtBrandName.setStyle("-fx-border-color: dodgerblue");
                    btnAddBrand.setDisable(false);
                } else {
                    txtBrandName.setStyle("-fx-border-color: red");
                    btnAddBrand.setDisable(true);
                }
            }
        }
    }
}
