package controller.customer.service;


import model.*;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerOrderService {
    String getOrderId() throws SQLException, ClassNotFoundException;

    boolean placeOrder(CustomerOrder order) throws SQLException, ClassNotFoundException;

    boolean saveOrderDetail(String orderId, List<CustomerProductDetail> productDetails) throws SQLException, ClassNotFoundException;

    boolean updateQty(int productId, int qty) throws SQLException, ClassNotFoundException;

    ArrayList<CustomerOrderDetailHistory> getAllCustomerDetailHistory() throws SQLException, ClassNotFoundException;

    List<CustomerOrderDetailHistory> searchCustomerHistoryByNIC(String value) throws SQLException, ClassNotFoundException;
}
