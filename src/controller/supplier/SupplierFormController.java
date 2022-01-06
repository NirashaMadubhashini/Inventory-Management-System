package controller.supplier;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Supplier;


import java.util.regex.Pattern;

public class SupplierFormController extends SupplierController{
    public AnchorPane SupplierFormContext;
    public TextField txtSupplierNIC;
    public TextField txtSupplierName;
    public TextField txtSupplierEmail;
    public TextField txtSupplierDescription;
    public Button btnAddSupplier;


    public void initialize() {
        btnAddSupplier.setDisable(true);

    }

    public void AddSupplierOnAction(ActionEvent actionEvent) {
      if (!txtSupplierNIC.getText().trim().isEmpty() && !txtSupplierName.getText().isEmpty()
      && !txtSupplierEmail.getText().trim().isEmpty() && !txtSupplierDescription.getText().trim().isEmpty()) {

          Supplier s = new Supplier(
                  txtSupplierNIC.getText(), txtSupplierName.getText(), txtSupplierEmail.getText(),
                  txtSupplierDescription.getText()

          );


          try {
              if (new SupplierFormController().saveSupplier(s)) {
                  new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();


              } else {
                  new Alert(Alert.AlertType.WARNING, "Try Again..").show();
              }


              Stage stage = (Stage) btnAddSupplier.getScene().getWindow();
              stage.close();


          } catch (Exception e) {
              new Alert(Alert.AlertType.WARNING, e.getMessage());
              e.printStackTrace();
          }
        }else {
          new Alert(Alert.AlertType.WARNING, "Cannot Be Empty Try Again").show();
         }
      }




    public void SupplierNIC_keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {

            String regEx = "^^[0-9 A-z]{0,}$";
            String typeText = txtSupplierNIC.getText();
            Pattern compile = Pattern.compile(regEx);
            boolean matches = compile.matcher(typeText).matches();


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches){
                    txtSupplierNIC.setStyle("-fx-border-color: dodgerblue");
                    txtSupplierName.requestFocus();
                }else{
                    txtSupplierNIC.setStyle("-fx-border-color: red");
                }
            }
        }
    }

    public void SupplierName_keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {

            String regEx = "^[A-z]{0,}$";
            String typeText = txtSupplierName.getText();
            Pattern compile = Pattern.compile(regEx);
            boolean matches = compile.matcher(typeText).matches();


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches){
                    txtSupplierName.setStyle("-fx-border-color: dodgerblue");
                    txtSupplierEmail.requestFocus();
                }else{
                    txtSupplierName.setStyle("-fx-border-color: red");
                }
            }
        }
    }

    public void SupplierEmail_keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {

            String regEx = "^^[1-9 A-z].{0,}$";
            String typeText = txtSupplierEmail.getText();
            Pattern compile = Pattern.compile(regEx);
            boolean matches = compile.matcher(typeText).matches();


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches){
                    txtSupplierEmail.setStyle("-fx-border-color: dodgerblue");
                    txtSupplierDescription.requestFocus();
                }else{
                    txtSupplierEmail.setStyle("-fx-border-color: red");
                }
            }
        }
    }

    public void SupplierDescription_keyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {

            String regEx = "^^[1-9 A-z]{0,}$";
            String typeText = txtSupplierDescription.getText();
            Pattern compile = Pattern.compile(regEx);
            boolean matches = compile.matcher(typeText).matches();


            if (keyEvent.getCode() == KeyCode.ENTER) {
                if (matches){
                    txtSupplierDescription.setStyle("-fx-border-color: dodgerblue");
                    btnAddSupplier.setDisable(false);
                }else{
                    txtSupplierDescription.setStyle("-fx-border-color: red");
                    btnAddSupplier.setDisable(true);
                }
            }
        }
    }
}
