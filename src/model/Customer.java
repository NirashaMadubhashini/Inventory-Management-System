package model;

public class Customer {
    private String customerNIC;
    private String name;
    private String mobileNumber;

    public Customer() {
    }

    public Customer(String customerNIC, String name, String mobileNumber) {
        this.setCustomerNIC(customerNIC);
        this.setName(name);
        this.setMobileNumber(mobileNumber);
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
}
