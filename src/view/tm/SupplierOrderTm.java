package view.tm;



public class SupplierOrderTm {
     private String orderId;
     private String supplierNIC;
     private int productId;
     private String productName;
     private int qty;
     private double price;
     private double total;

    public SupplierOrderTm() {
    }

    public SupplierOrderTm(String orderId, String supplierNIC, int productId,
                           String productName, int qty, double price,double total) {
        this.setOrderId(orderId);
        this.setSupplierNIC(supplierNIC);
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

    public String getSupplierNIC() {
        return supplierNIC;
    }

    public void setSupplierNIC(String supplierNIC) {
        this.supplierNIC = supplierNIC;
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
