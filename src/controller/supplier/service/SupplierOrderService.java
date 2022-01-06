package controller.supplier.service;


import model.SupplierOrder;
import model.SupplierOrderDetailHistory;
import model.SupplierProductDetail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierOrderService {
    String getOrderId() throws SQLException, ClassNotFoundException;

    boolean placeOrder(SupplierOrder order) throws SQLException, ClassNotFoundException;

    boolean saveOrderDetail(String orderId, List<SupplierProductDetail> productDetails) throws SQLException, ClassNotFoundException;

    boolean updateQty(int productId, int qty) throws SQLException, ClassNotFoundException;

    ArrayList<SupplierOrderDetailHistory> getAllSupplierDetailHistory() throws SQLException, ClassNotFoundException;

    List<SupplierOrderDetailHistory> searchSupplierHistoryByNIC(String value) throws SQLException, ClassNotFoundException;

}
