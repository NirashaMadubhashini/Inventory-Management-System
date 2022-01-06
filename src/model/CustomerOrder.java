package model;

import java.util.List;

public class CustomerOrder {
    private String customerOrderId;
    private String customerNIC;
    private String date;
    private String time;
    private List<CustomerProductDetail> productDetails;

    public List<CustomerProductDetail> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<CustomerProductDetail> productDetails) {
        this.productDetails = productDetails;
    }

    public CustomerOrder(String customerOrderId, String customerNIC, String date, String time, List<CustomerProductDetail> productDetails) {
        this.customerOrderId = customerOrderId;
        this.customerNIC = customerNIC;
        this.date = date;
        this.time = time;
        this.productDetails = productDetails;
    }

    public CustomerOrder() {
    }

    public CustomerOrder(String customerOrderId, String customerNIC, String date, String time) {
        this.customerOrderId = customerOrderId;
        this.customerNIC = customerNIC;
        this.date = date;
        this.time = time;
    }

    public CustomerOrder(String customerOrderId, String customerNIC) {
        this.customerOrderId = customerOrderId;
        this.customerNIC = customerNIC;
    }

    public String getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(String customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public String getCustomerNIC() {
        return customerNIC;
    }

    public void setCustomerNIC(String customerNIC) {
        this.customerNIC = customerNIC;
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
