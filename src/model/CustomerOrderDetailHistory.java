package model;

public class CustomerOrderDetailHistory {
    private String orderId;
    private String customerNIC;
    private int productId;
    private String productName;
    private double unitPrice;
    private int qtyOnHand;
    private double total;
    private String date;
    private String time;

    public CustomerOrderDetailHistory() {
    }

    public CustomerOrderDetailHistory(String orderId, String customerNIC, int productId, String productName,
                                      double unitPrice, int qtyOnHand, double total, String date, String time) {
        this.setOrderId(orderId);
        this.setCustomerNIC(customerNIC);
        this.setProductId(productId);
        this.setProductName(productName);
        this.setUnitPrice(unitPrice);
        this.setQtyOnHand(qtyOnHand);
        this.setTotal(total);
        this.setDate(date);
        this.setTime(time);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerNIC() {
        return customerNIC;
    }

    public void setCustomerNIC(String customerNIC) {
        this.customerNIC = customerNIC;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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
}
