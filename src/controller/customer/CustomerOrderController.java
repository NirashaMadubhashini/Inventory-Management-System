package controller.customer;

import controller.customer.service.CustomerOrderService;
import db.DbConnection;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrderController implements CustomerOrderService {
    public String getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT customerOrderId FROM CustomerOrder ORDER BY customerOrderId DESC LIMIT 1"
                ).executeQuery();
        if (rst.next()) {

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "O-00" + tempId;
            } else if (tempId <= 99) {
                return "O-0" + tempId;
            } else {
                return "O-" + tempId;
            }

        } else {
            return "O-001";
        }
    }

    @Override
    public boolean placeOrder(CustomerOrder order) throws SQLException, ClassNotFoundException {
        Connection con = null;
        try {
            con = DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement stm = con.
                    prepareStatement("INSERT INTO customerorder VALUES(?,?,?,?)");


            stm.setObject(1, order.getCustomerOrderId());
            stm.setObject(2, order.getCustomerNIC());
            stm.setObject(3, order.getDate());
            stm.setObject(4, order.getTime());

            if (stm.executeUpdate() > 0) {
                if (saveOrderDetail(order.getCustomerOrderId(), order.getProductDetails())) {
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
    public boolean saveOrderDetail(String orderId, List<CustomerProductDetail> productDetails) throws SQLException, ClassNotFoundException {
        for (CustomerProductDetail temp : productDetails) {
            PreparedStatement stm = DbConnection.getInstance().
                    getConnection().
                    prepareStatement("INSERT INTO customerorderdetail VALUES(?,?,?,?,?,?,?)");

            stm.setObject(1, orderId);
            stm.setObject(2, temp.getProductId());
            stm.setObject(3, temp.getPrice());
            stm.setObject(4, temp.getQty());
            stm.setObject(5, temp.getTotal());
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
        PreparedStatement stm = DbConnection.getInstance().getConnection()
                .prepareStatement
                        ("UPDATE product SET qty=(qty -" + qty
                                + ") WHERE productId='" + productId + "'");
        return stm.executeUpdate() > 0;
    }

    @Override
    public ArrayList<CustomerOrderDetailHistory> getAllCustomerDetailHistory() throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT cod.customerOrderId," +
                "o.customerNIC," +
                "cod.productId," +
                "p.productName," +
                "cod.unitPrice," +
                "cod.qtyOnHand," +
                "cod.total," +
                "cod.date," +
                "cod.time " +
                "FROM customerorderdetail cod " +
                "LEFT JOIN customerorder o ON cod.customerOrderId = o.customerOrderId " +
                "LEFT JOIN product p ON cod.productId = p.productId");

        ResultSet rst = stm.executeQuery();
        ArrayList<CustomerOrderDetailHistory> customerOrderDetailHistories = new ArrayList<>();

        while (rst.next()) {
            customerOrderDetailHistories.add(new CustomerOrderDetailHistory(
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
        return customerOrderDetailHistories;
    }

    @Override
    public List<CustomerOrderDetailHistory> searchCustomerHistoryByNIC(String value) throws SQLException, ClassNotFoundException {
        Connection con=DbConnection.getInstance().getConnection();

        PreparedStatement stm = con.prepareStatement("SELECT cod.customerOrderId," +
                "o.customerNIC," +
                "cod.productId," +
                "p.productName," +
                "cod.unitPrice," +
                "cod.qtyOnHand," +
                "cod.total," +
                "cod.date," +
                "cod.time " +
                "FROM  customerorderdetail cod " +
                "LEFT JOIN customerorder o ON cod.customerOrderId = o.customerOrderId " +
                "LEFT JOIN product p ON cod.productId = p.productId " +
                "WHERE o.customerNIC  LIKE '%"+ value + "%'");
        ResultSet rst=stm.executeQuery();

        List<CustomerOrderDetailHistory>customerOrderDetailHistories=new ArrayList<>();

        while (rst.next()){
            customerOrderDetailHistories.add(new CustomerOrderDetailHistory(
                    rst.getString("customerOrderId"),
                    rst.getString("customerNIC"),
                    rst.getInt("productId"),
                    rst.getString("productName"),
                    rst.getDouble("unitPrice"),
                    rst.getInt("qtyOnHand"),
                    rst.getDouble("total"),
                    rst.getString("date"),
                    rst.getString("time")

            ));
        }
        return customerOrderDetailHistories;
    }

}

