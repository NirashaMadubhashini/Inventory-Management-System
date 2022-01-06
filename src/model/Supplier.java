package model;

public class Supplier {
    private String supplierNIC;
    private String supplierName;
    private String email;
    private String description;



    public Supplier(String supplierNIC, String supplierName, String email, String description) {
        this.setSupplierNIC(supplierNIC);
        this.setSupplierName(supplierName);
        this.setEmail(email);
        this.setDescription(description);
    }

    public Supplier(String supplierName, String email, String description) {
        this.supplierName=supplierName;
        this.email=email;
        this.description=description;
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
}
