package model;

public class CustomerOrderDetail {
    private String customerOrderId;
    private int productId;
    private double unitPrice;
    private int qtyOnHand;
    private double total;
    private String date;
    private String time;

    public CustomerOrderDetail() {
    }

    public CustomerOrderDetail(String customerOrderId, int productId, double unitPrice, int qtyOnHand, double total,String date, String time) {
        this.customerOrderId = customerOrderId;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.qtyOnHand = qtyOnHand;
        this.setTotal(total);
        this.date = date;
        this.time = time;
    }

    public String getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(String customerOrderId) {
        this.customerOrderId = customerOrderId;
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

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
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
