package model;

public class Product {
    private int productId;
    private String serialNumber;
    private int brandId;
    private String productName;
    private int qty;
    private double price;
    private String date;
    private String time;


    public Product(int productId, String serialNumber, int brandId, String productName, int qty, double price,
                   String date, String time) {
        this.productId = productId;
        this.serialNumber = serialNumber;
        this.brandId = brandId;
        this.productName = productName;
        this.setQty(qty);
        this.price = price;
        this.date = date;
        this.time = time;
    }

    public Product(String serialNumber, String productName,int qty, Double price, String date, String time) {
        this.serialNumber=serialNumber;
        this.productName=productName;
        this.qty=qty;
        this.price=price;
        this.date=date;
        this.time=time;
    }

    public Product(int productId, String productName,int qty, double price) {
        this.productId=productId;
        this.productName=productName;
        this.qty=qty;
        this.price=price;
    }

    public Product( int productId,int qty, double price) {
        this.productId=productId;
        this.qty=qty;
        this.price=price;
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

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
