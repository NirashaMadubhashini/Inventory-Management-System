package controller.supplier;

import controller.supplier.service.SupplierOrderService;
import db.DbConnection;
import model.SupplierOrder;
import model.SupplierOrderDetailHistory;
import model.SupplierProductDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierOrderController implements SupplierOrderService {
    @Override
    public String getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement(
                "SELECT orderId FROM SupplierOrder ORDER BY orderId DESC LIMIT 1"
        ).executeQuery();
        if (rst.next()) {

            int tempId = Integer.parseInt(rst.getString(1).split("-")[1]);
            tempId = tempId + 1;

            if (tempId <= 9) {
                return "0-00" + tempId;
            } else if (tempId <= 99) {
                return "0-0" + tempId;
            } else {
                return "0-" + tempId;
            }
        } else {
            return "0-001";
        }

    }

    @Override
    public boolean placeOrder(SupplierOrder order) throws SQLException, ClassNotFoundException {
        Connection con = null;
        try {
            con = DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement stm = con.prepareStatement(
                    "INSERT INTO supplierOrder VALUES (?,?,?,?)"
            );
            stm.setObject(1, order.getOrderId());
            stm.setObject(2, order.getSupplierNIC());
            stm.setObject(3, order.getOrderDate());
            stm.setObject(4, order.getOrderTime());

            if (stm.executeUpdate() > 0) {
                if (saveOrderDetail(order.getOrderId(), order.getProductDetails())) {
                    con.commit();
                    return true;
                } else {
                    con.rollback();
                    return false;
                }
            } else {
                con.rollback();
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }


    @Override
    public boolean saveOrderDetail(String orderId, List<SupplierProductDetail> productDetails) throws SQLException, ClassNotFoundException {
        for (SupplierProductDetail temp : productDetails) {
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement(
                    "INSERT INTO supplierOrderDetail VALUES (?,?,?,?,?,?,?)"
            );
            stm.setObject(1, orderId);
            stm.setObject(2, temp.getProductId());
            stm.setObject(3, temp.getPrice());
            stm.setObject(4, temp.getTotal());
            stm.setObject(5, temp.getQty());
            stm.setObject(6, temp.getDate());
            stm.setObject(7, temp.getTime());

            if (stm.executeUpdate() > 0) {

                if (updateQty(temp.getProductId(), temp.getQty())) {

                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateQty(int productId, int qty) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement(
                "UPDATE product SET qty = (qty + " + qty + ") WHERE productId = '" + productId + "'"
        );
        return stm.executeUpdate() > 0;
    }

    @Override
    public ArrayList<SupplierOrderDetailHistory> getAllSupplierDetailHistory() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT sod.orderId," +
                "o.supplierNIC," +
                "sod.productId," +
                "p.productName," +
                "sod.unitPrice," +
                "sod.qty," +
                "sod.total," +
                "sod.date," +
                "sod.time " +
                "FROM SupplierOrderDetail sod " +
                "LEFT JOIN SupplierOrder o ON sod.orderId = o.orderId " +
                "LEFT JOIN product p ON sod.productId = p.productId");

        ResultSet rst = stm.executeQuery();
        ArrayList<SupplierOrderDetailHistory> supplierOrderDetailHistories = new ArrayList<>();

        while (rst.next()) {
            supplierOrderDetailHistories.add(new SupplierOrderDetailHistory(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getInt(6),
                    rst.getDouble(7),
                    rst.getString(8),
                    rst.getString(9)
            ));
        }
        return supplierOrderDetailHistories;
    }

    @Override
    public List<SupplierOrderDetailHistory> searchSupplierHistoryByNIC(String value) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT sod.orderId," +
                "o.supplierNIC," +
                "sod.productId," +
                "p.productName," +
                "sod.unitPrice," +
                "sod.qty," +
                "sod.total," +
                "sod.date," +
                "sod.time " +
                "FROM SupplierOrderDetail sod " +
                "LEFT JOIN SupplierOrder o ON sod.orderId = o.orderId " +
                "LEFT JOIN product p ON sod.productId = p.productId " +
                "WHERE o.supplierNIC  LIKE '%" + value + "%'");
        ResultSet rst = stm.executeQuery();

        List<SupplierOrderDetailHistory> supplierOrderDetailHistories = new ArrayList<>();

        while (rst.next()) {
            supplierOrderDetailHistories.add(new SupplierOrderDetailHistory(
                    rst.getString("orderId"),
                    rst.getString("supplierNIC"),
                    rst.getInt("productId"),
                    rst.getString("productName"),
                    rst.getDouble("unitPrice"),
                    rst.getInt("qty"),
                    rst.getDouble("total"),
                    rst.getString("date"),
                    rst.getString("time")
            ));
        }
        return supplierOrderDetailHistories;
    }


}
