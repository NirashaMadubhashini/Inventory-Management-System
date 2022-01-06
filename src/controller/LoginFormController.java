package controller;

import com.jfoenix.controls.JFXTextField;
import controller.login.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Login;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class LoginFormController {
    public AnchorPane loginFormContext;
    public JFXTextField txtUserName;
    public JFXTextField txtPassWord;

    public void openDashBoardOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {


        try {
            List<Login> allLogin = new LoginController().getAllLogin();

            allLogin.forEach(data ->{
                if (txtUserName.getText().equalsIgnoreCase(data.getUserName()) && txtPassWord.getText().equals(data.getPassword())) {
                    Stage window = (Stage) loginFormContext.getScene().getWindow();
                    try {
                        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if (txtPassWord.getText().equals("") && txtUserName.getText().equals("")){
                    new Alert(Alert.AlertType.WARNING, "Password And User Name is Empty", ButtonType.CLOSE).show();

                }
                else if (txtPassWord.getText().equals("")){
                    new Alert(Alert.AlertType.WARNING, "Password Is Empty", ButtonType.CLOSE).show();

                }else if (txtUserName.getText().equals("")){
                    new Alert(Alert.AlertType.WARNING, "UserName Is Empty", ButtonType.CLOSE).show();

                }
                else {
                    new Alert(Alert.AlertType.WARNING, "Incorrect User Name, Password. Try again..", ButtonType.CLOSE).show();
                }
            });
        }catch (Exception e){
            new Alert(Alert.AlertType.WARNING, e.getMessage(), ButtonType.CLOSE).show();

        }


    }

    public void moveToPassword(ActionEvent actionEvent) {
        txtPassWord.requestFocus();
    }
}

