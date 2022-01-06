package view.tm;

import javafx.scene.control.Button;

public class CustomerTm {
     private String customerNIC;
     private String name;
     private String mobileNumber;
     private Button update;
     private Button delete;

    public CustomerTm() {
    }

    public CustomerTm(String customerNIC, String name, String mobileNumber, Button update, Button delete) {
        this.setCustomerNIC(customerNIC);
        this.setName(name);
        this.setMobileNumber(mobileNumber);
        this.setUpdate(update);
        this.setDelete(delete);
    }

    public String getCustomerNIC() {
        return customerNIC;
    }

    public void setCustomerNIC(String customerNIC) {
        this.customerNIC = customerNIC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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
