package controller.customer;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Customer;

import java.util.regex.Pattern;

public class CustomerFormController extends CustomerController{
    public AnchorPane CustomerFormContext;
    public Button btnAddCustomer;
    public TextField txtCustomerNIC;
    public TextField txtCustomerName;
    public TextField txtCustomerNumber;

    public void initialize() {
        btnAddCustomer.setDisable(true);

    }

    public void AddCustomerOnAction(ActionEvent actionEvent) {
        if (!txtCustomerNIC.getText().trim().isEmpty() && !txtCustomerName.getText().trim().isEmpty()
                && !txtCustomerNumber.getText().trim().isEmpty()) {

            Customer customer = new Customer(
                    txtCustomerNIC.getText(), txtCustomerName.getText(), txtCustomerNumber.getText()

            );


            try {
                if (new CustomerFormController().saveCustomer(customer)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();


                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again..").show();
                }


                Stage stage = (Stage) btnAddCustomer.getScene().getWindow();
                stage.close();


            } catch (Exception e) {
                new Alert(Alert.AlertType.WARNING, e.getMessage());
                e.printStackTrace();
            }

        } else {
            new Alert(Alert.AlertType.WARNING, "Cannot Be Empty Try Again").show();

        }
    }

    public void CustomerNIC_keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {

            String regEx = "^[0-9 A-z]{0,}$";
            String typeText = txtCustomerNIC.getText();
            Pattern compile = Pattern.compile(regEx);
            boolean matches = compile.matcher(typeText).matches();


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtCustomerNIC.setStyle("-fx-border-color: dodgerblue");
                    txtCustomerName.requestFocus();
                } else {
                    txtCustomerNIC.setStyle("-fx-border-color: red");
                }
            }
        }
    }

    public void CustomerName_keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {

            String regEx = "^[A-z]{0,}$";
            String typeText = txtCustomerName.getText();
            Pattern compile = Pattern.compile(regEx);
            boolean matches = compile.matcher(typeText).matches();


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtCustomerName.setStyle("-fx-border-color: dodgerblue");
                    txtCustomerNumber.requestFocus();
                } else {
                    txtCustomerName.setStyle("-fx-border-color: red");
                }
            }
        }
    }

    public void CustomerNumber_keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {

            String regEx = "^[0-9].{0,}$";
            String typeText = txtCustomerNumber.getText();
            Pattern compile = Pattern.compile(regEx);
            boolean matches = compile.matcher(typeText).matches();


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtCustomerNumber.setStyle("-fx-border-color: dodgerblue");
                    btnAddCustomer.setDisable(false);
                } else {
                    txtCustomerNumber.setStyle("-fx-border-color: red");
                    btnAddCustomer.setDisable(true);
                }
            }
        }
    }
}
