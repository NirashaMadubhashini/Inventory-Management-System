package view.tm;



public class CustomerOrderTm {
     private String orderId;
     private String customerName;
     private int productId;
     private String productName;
     private int qty;
     private double price;
     private double total;

    public CustomerOrderTm() {
    }

    public CustomerOrderTm(String orderId, String customerName, int productId, String productName, int qty, double price,double total) {
        this.setOrderId(orderId);
        this.setCustomerName(customerName);
        this.setProductId(productId);
        this.setProductName(productName);
        this.setQty(qty);
        this.setPrice(price);
        this.setTotal(total);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
