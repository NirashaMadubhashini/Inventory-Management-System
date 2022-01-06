package model;

import java.util.List;

public class SupplierOrder {
    private String orderId;
    private String supplierNIC;
    private String orderDate;
    private String orderTime;
    private List<SupplierProductDetail>productDetails;

    public SupplierOrder() {
    }

    public SupplierOrder(String orderId, String supplierNIC, String orderDate, String orderTime, List<SupplierProductDetail> productDetails) {
        this.setOrderId(orderId);
        this.setSupplierNIC(supplierNIC);
        this.setOrderDate(orderDate);
        this.setOrderTime(orderTime);
        this.setProductDetails(productDetails);
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

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public List<SupplierProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<SupplierProductDetail> productDetails) {
        this.productDetails = productDetails;
    }
}
