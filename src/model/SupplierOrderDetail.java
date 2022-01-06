package model;

public class SupplierOrderDetail {
    private String orderId;
    private int productId;
    private double unitPrice;
    private int qty;
    private double total;
    private String date;
    private String time;

    public SupplierOrderDetail() {
    }

    public SupplierOrderDetail(String orderId, int productId, double unitPrice, int qty, double total,String date, String time) {
        this.orderId = orderId;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.total=total;
        this.date = date;
        this.time = time;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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
