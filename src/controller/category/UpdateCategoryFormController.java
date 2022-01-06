package controller.category;

import controller.category.service.CategoryService;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Category;
import util.TableCategoryLoadEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UpdateCategoryFormController extends CategoryFormController implements CategoryService, Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public AnchorPane UpdateCategoryFormContext;
    public TextField txtUpdateCategoryName;
    public TextField txtUpdateCategoryDescription;
    public int categoryId;
    public Button btnUpdateCategory;


    private TableCategoryLoadEvent event;

    public void UpdateCategoryOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Category c = new Category(
                categoryId,
                txtUpdateCategoryName.getText(),
                txtUpdateCategoryDescription.getText()
        );

        try {
            if (new CategoryController().updateCategory(c)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();

                Stage stage = (Stage) btnUpdateCategory.getScene().getWindow();
                stage.close();

                event.loadData();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }




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


    public void setEvent(TableCategoryLoadEvent event) {
        this.event = event;
    }

    public void moveUpdateCategoryNameOnAction(ActionEvent actionEvent) {
        txtUpdateCategoryDescription.requestFocus();
    }


}
