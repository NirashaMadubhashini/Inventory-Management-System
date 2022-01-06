package controller;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class DashBoardFormController {
    public AnchorPane DashBoardFormContext;
    public Label lblDate;
    public Label lblTime;



    public void initialize() throws IOException, SQLException, ClassNotFoundException {

        loadDateAndTime();

    }


    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }


    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) DashBoardFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
    }

    public void AddCategoryOnAction(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/CategoryForm.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Sell-X");
        stage.getIcons().add(new Image("assets/laptop.png"));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();

    }

    public void AddBrandOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/BrandForm.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Sell-X");
        stage.getIcons().add(new Image("assets/laptop.png"));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    public void AddProductOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ProductForm.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Sell-X");
        stage.getIcons().add(new Image("assets/laptop.png"));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    public void AddSupplierOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/SupplierForm.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Sell-X");
        stage.getIcons().add(new Image("assets/laptop.png"));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    public void AddSCustomerOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/CustomerForm.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Sell-X");
        stage.getIcons().add(new Image("assets/laptop.png"));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    public void ManageCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) DashBoardFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ManageCustomerForm.fxml"))));
    }

    public void ManageCategoryOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) DashBoardFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ManageCategoryForm.fxml"))));
    }

    public void ManageBrandOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) DashBoardFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ManageBrandForm.fxml"))));
    }

    public void ManageProductOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) DashBoardFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ManageProductForm.fxml"))));
    }

    public void ManageSupplierOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) DashBoardFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ManageSupplierForm.fxml"))));
    }

    public void CustomerPlaceOrderOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) DashBoardFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/CustomerPlaceOrderForm.fxml"))));
    }

    public void SupplierPlaceOrderOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) DashBoardFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/SupplierPlaceOrderForm.fxml"))));
    }

    public void SupplierOrderHistoryOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) DashBoardFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/SupplierOrderHistoryForm.fxml"))));
    }

    public void CustomerOrderHistoryOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) DashBoardFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/CustomerOrderHistoryForm.fxml"))));
    }


}
