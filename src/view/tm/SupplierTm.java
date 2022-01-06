package view.tm;

import javafx.scene.control.Button;

public class SupplierTm {
     private String supplierNIC;
     private String supplierName;
     private String email;
     private String description;
     private Button update;
     private Button delete;



    public SupplierTm(String supplierNIC, String supplierName, String email, String description, Button update, Button delete) {
        this.setSupplierNIC(supplierNIC);
        this.setSupplierName(supplierName);
        this.setEmail(email);
        this.setDescription(description);
        this.setUpdate(update);
        this.setDelete(delete);
    }




    public String getSupplierNIC() {
        return supplierNIC;
    }

    public void setSupplierNIC(String supplierNIC) {
        this.supplierNIC = supplierNIC;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Button getUpdate() {
        return update;
    }

    public void setUpdate(Button update) {
        this.update = update;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }
}
