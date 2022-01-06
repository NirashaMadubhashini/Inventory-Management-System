package controller.supplier;

import controller.supplier.service.SupplierService;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Supplier;
import util.TableSupplierLoadEvent;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class UpdateSupplierFormController extends SupplierController implements SupplierService {
    public AnchorPane UpdateSupplierFormContext;

    public String supplierNIC;

    public TextField txtUpdateSupplierName;
    public TextField txtUpdateSupplierEmail;
    public TextField txtUpdateSupplierDescription;

    public Button btnUpdateSupplier;

    private TableSupplierLoadEvent event;


    public void UpdateSupplierOnAction(ActionEvent actionEvent) {
        Supplier s = new Supplier(
                supplierNIC,
                txtUpdateSupplierName.getText(),
                txtUpdateSupplierEmail.getText(),
                txtUpdateSupplierDescription.getText()
        );

        try {
            if (new SupplierController().updateSupplier(s)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();

                Stage stage = (Stage) btnUpdateSupplier.getScene().getWindow();
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

    public void setEvent(TableSupplierLoadEvent event) {
        this.event = event;
    }

    public void SupplierName_keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {

            String regEx = "^[A-z ].{0,}$";
            String typeText = txtUpdateSupplierName.getText();
            Pattern compile = Pattern.compile(regEx);
            boolean matches = compile.matcher(typeText).matches();


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches){
                    txtUpdateSupplierName.setStyle("-fx-border-color: dodgerblue");
                    txtUpdateSupplierEmail.requestFocus();
                }else{
                    txtUpdateSupplierName.setStyle("-fx-border-color: red");
                }
            }
        }
    }

    public void SupplierEmail_keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {

            String regEx = "^^[1-9 A-z ].{0,}$";
            String typeText = txtUpdateSupplierEmail.getText();
            Pattern compile = Pattern.compile(regEx);
            boolean matches = compile.matcher(typeText).matches();


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches){
                    txtUpdateSupplierEmail.setStyle("-fx-border-color: dodgerblue");
                    txtUpdateSupplierDescription.requestFocus();
                }else{
                    txtUpdateSupplierEmail.setStyle("-fx-border-color: red");
                }
            }
        }
    }

    public void SupplierDescription_keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {

            String regEx = "^^[1-9 A-z ].{0,}$";
            String typeText = txtUpdateSupplierDescription.getText();
            Pattern compile = Pattern.compile(regEx);
            boolean matches = compile.matcher(typeText).matches();


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches){
                    txtUpdateSupplierDescription.setStyle("-fx-border-color: dodgerblue");
                }else{
                    txtUpdateSupplierDescription.setStyle("-fx-border-color: red");
                }
            }
        }
    }
}
