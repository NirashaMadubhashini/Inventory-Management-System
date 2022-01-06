package controller.category;

import controller.category.service.CategoryService;
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
import model.Category;
import util.TableCategoryLoadEvent;
import view.tm.CategoryTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManageCategoryFormController extends CategoryController implements CategoryService, TableCategoryLoadEvent {
    public AnchorPane ManageCategoryFormContext;
    public TableView<CategoryTm> tblManageCategory;

    public TableColumn colCategoryId;
    public TableColumn colCategoryName;
    public TableColumn colCategoryDescription;
    public TableColumn colCategoryDate;
    public TableColumn colCategoryTime;
    public TableColumn colCategoryUpdate;
    public TableColumn colCategoryDelete;
    public TextField txtCategorySearch;


    public void initialize() {
        colCategoryId.setCellValueFactory(new PropertyValueFactory<>("CategoryId"));
        colCategoryName.setCellValueFactory(new PropertyValueFactory<>("CategoryName"));
        colCategoryDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colCategoryDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colCategoryTime.setCellValueFactory(new PropertyValueFactory<>("Time"));
        colCategoryUpdate.setCellValueFactory(new PropertyValueFactory<>("Update"));
        colCategoryDelete.setCellValueFactory(new PropertyValueFactory<>("Delete"));

        tblManageCategory.getColumns().setAll(colCategoryId, colCategoryName, colCategoryDescription, colCategoryDate,
                colCategoryTime, colCategoryUpdate, colCategoryDelete);



        txtCategorySearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                search(newValue);
            }
        });

        try {
            loadAllCategories(new CategoryController().getAllCategories());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }




    private void loadAllCategories(ArrayList<Category> categories) throws SQLException, ClassNotFoundException {
        try {
            ObservableList<CategoryTm> tmObservableList = FXCollections.observableArrayList();

            for (Category temp : categories
            ) {
                Button btnUpdate = new Button("Update");
                Button btnDelete = new Button("Delete");
                tmObservableList.add(
                        new CategoryTm(temp.getCategoryId(), temp.getCategoryName(), temp.getDescription(), temp.getDate(), temp.getTime(), btnUpdate, btnDelete)
                );

                btnDelete.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "Are you sure you want to delete this Category?", yes, no);
                    alert.setTitle("Conformation Alert");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.orElse(no) == yes) {

                        try {
                            deleteCategory(temp);
                            categories.remove(temp);
                            loadAllCategories(categories);

                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } catch (ClassNotFoundException classNotFoundException) {
                            classNotFoundException.printStackTrace();
                        }
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
            tblManageCategory.setItems(tmObservableList);


        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.setTitle("Error Alert");
        }

    }

    public void showUpdateForm(Category table) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/UpdateCategoryForm.fxml"));
            Parent parent = loader.load();
            UpdateCategoryFormController controller = loader.<UpdateCategoryFormController>getController();
            controller.txtUpdateCategoryDescription.setText(table.getDescription());
            controller.txtUpdateCategoryName.setText(table.getCategoryName());
            controller.categoryId = table.getCategoryId();
            controller.setEvent(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void deleteCategory(Category temp) {
        try {
            if (new CategoryController().deleteCategory(temp.getCategoryName())) {

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

    public void CategorySearchOnAction(ActionEvent actionEvent) {
        search(txtCategorySearch.getText());
    }

    private void search(String value) {
        try {
            List<Category> categories = new CategoryFormController().searchCategory(value);
            loadAllCategories((ArrayList<Category>) categories);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }



    public void BackCategoryOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) ManageCategoryFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashBoardForm.fxml"))));
    }

    @Override
    public void loadData() {
        try {
            loadAllCategories(new CategoryController().getAllCategories());        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
