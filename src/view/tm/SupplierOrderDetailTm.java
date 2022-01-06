package view.tm;



public class SupplierOrderDetailTm {
      private String orderId;
      private String supplierNIC;
      private int productId;
      private String productName;
      private double unitPrice;
      private int qtyOnHand;
      private double total;
      private String date;
      private String time;

    public SupplierOrderDetailTm() {
    }

    public SupplierOrderDetailTm(String orderId, String supplierNIC,
                                 int productId, String productName, double unitPrice, int qtyOnHand, double total, String date, String time) {
        this.setOrderId(orderId);
        this.setSupplierNIC(supplierNIC);
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
