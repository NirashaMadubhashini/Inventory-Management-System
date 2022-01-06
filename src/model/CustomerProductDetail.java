package model;

public class CustomerProductDetail {
    private int productId;
    private int qty;
    private double price;
    private double total;
    private String date;
    private String time;

    public CustomerProductDetail() {
    }

    public CustomerProductDetail(int productId, int qty, double price,double total, String date, String time) {
        this.setProductId(productId);
        this.setQty(qty);
        this.setPrice(price);
        this.setTotal(total);
        this.setDate(date);
        this.setTime(time);
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
