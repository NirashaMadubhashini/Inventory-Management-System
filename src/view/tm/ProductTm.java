package view.tm;

import javafx.scene.control.Button;

public class ProductTm {
     private int productId;
     private String serialNumber;
    private String productName;
     private String brandName;
     private int qty;
     private double price;
     private String date;
     private String time;
     private Button update;
     private Button delete;

    public ProductTm() {
    }

    public ProductTm(int productId, String serialNumber, String productName, String brandName, int qty, double price,
                     String date, String time, Button update, Button delete) {
        this.setProductId(productId);
        this.setSerialNumber(serialNumber);
        this.setProductName(productName);
        this.setBrandName(brandName);
        this.setQty(qty);
        this.setPrice(price);
        this.setDate(date);
        this.setTime(time);
        this.setUpdate(update);
        this.setDelete(delete);
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
