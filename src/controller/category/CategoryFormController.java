package controller.category;


import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Category;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

public class CategoryFormController extends CategoryController {
    public AnchorPane CategoryFormContext;
    public TextField txtCategoryName;
    public TextField txtCategoryDescription;
    public Button btnAddCategory;


    public void initialize() throws IOException, SQLException, ClassNotFoundException {

        btnAddCategory.setDisable(true);

    }



    private boolean isExists(Category c) throws SQLException, ClassNotFoundException {

        ArrayList<Category> allCategories = new CategoryFormController().getAllCategories();

        for (int i = 0; i < allCategories.size(); i++) {
            if (c.getCategoryName().equals(allCategories.get(i).getCategoryName())) {
                return true;
            }
        }
        return false;
    }

    public void AddCategoryOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (!txtCategoryDescription.getText().trim().isEmpty() && !txtCategoryName.getText().trim().isEmpty()) {


            Date date1 = new Date();
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String date = f.format(date1);

            LocalTime currentTime = LocalTime.now();
            String time = "";
            time += currentTime.getHour() + " : " + currentTime.getMinute() +
                    " : " + currentTime.getSecond();


            Category c = new Category(
                    txtCategoryName.getText(), txtCategoryDescription.getText(),
                    date, time
            );
            if (!isExists(c)) {
                try {
                    if (new CategoryFormController().saveCategory(c)) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();

                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                    }

                    Stage stage = (Stage) btnAddCategory.getScene().getWindow();
                    stage.close();

                } catch (Exception e) {
                    new Alert(Alert.AlertType.WARNING, e.getMessage());
                    e.printStackTrace();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Category ALready Exisits").show();
            }

        } else {
            new Alert(Alert.AlertType.WARNING, "Cannot Be Empty Try Again").show();
            txtCategoryName.setStyle("-fx-border-color: red");
            txtCategoryDescription.setStyle("-fx-border-color: red");
        }


    }




    public void CategoryName_keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {

            String regEx = "^[A-z 0-9]{0,}$";
            String typeText = txtCategoryName.getText();
            Pattern compile = Pattern.compile(regEx);
            boolean matches = compile.matcher(typeText).matches();


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtCategoryName.setStyle("-fx-border-color: dodgerblue");
                    txtCategoryDescription.requestFocus();
                } else {
                    txtCategoryName.setStyle("-fx-border-color: red");
                }
            }
        }
    }

    public void CategoryDescription_keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {

            String regEx = "^[A-z 0-9]{0,}$";
            String typeText = txtCategoryDescription.getText();
            Pattern compile = Pattern.compile(regEx);
            boolean matches = compile.matcher(typeText).matches();


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtCategoryDescription.setStyle("-fx-border-color: dodgerblue");
                    btnAddCategory.setDisable(false);
                } else {
                    txtCategoryDescription.setStyle("-fx-border-color: red");
                    btnAddCategory.setDisable(true);
                }
            }
        }
    }
}
