package controller.customer;

import controller.customer.service.CustomerService;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Customer;
import util.TableCustomerLoadEvent;


import java.sql.SQLException;
import java.util.regex.Pattern;

public class UpdateCustomerFormController extends CustomerController implements CustomerService {
    public AnchorPane UpdateCustomerFormContext;

    public String customerNIC;

    public Button btnUpdateCustomer;
    public TextField txtUpdateCustomerName;
    public TextField txtUpdateCustomerMobileNumber;

    private TableCustomerLoadEvent event;

    public void UpdateCustomerOnAction(ActionEvent actionEvent) {
        Customer customer=new Customer(
            customerNIC,
            txtUpdateCustomerName.getText(),
            txtUpdateCustomerMobileNumber.getText()
        );
        try {
            if (new CustomerController().updateCustomer(customer)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();

                Stage stage = (Stage) btnUpdateCustomer.getScene().getWindow();
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

    public void setEvent(TableCustomerLoadEvent event){
        this.event= event;
    }

    public void CustomerName_keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {

            String regEx = "^[A-z ].{0,}$";
            String typeText = txtUpdateCustomerName.getText();
            Pattern compile = Pattern.compile(regEx);
            boolean matches = compile.matcher(typeText).matches();


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtUpdateCustomerName.setStyle("-fx-border-color: dodgerblue");
                    txtUpdateCustomerMobileNumber.requestFocus();
                } else {
                    txtUpdateCustomerName.setStyle("-fx-border-color: red");
                }
            }
        }
    }

    public void CustomerMobile_keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {

            String regEx = "^^[1-9].{0,}$";
            String typeText = txtUpdateCustomerMobileNumber.getText();
            Pattern compile = Pattern.compile(regEx);
            boolean matches = compile.matcher(typeText).matches();


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches) {
                    txtUpdateCustomerMobileNumber.setStyle("-fx-border-color: dodgerblue");

                } else {
                    txtUpdateCustomerMobileNumber.setStyle("-fx-border-color: red");

                }
            }
        }
    }
}
