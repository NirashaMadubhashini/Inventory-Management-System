package controller.customer;

import controller.customer.service.CustomerService;
import db.DbConnection;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController implements CustomerService {

    public List<String> getCustomerNICs() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Customer").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }



    @Override
    public boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Customer VALUES (?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, customer.getCustomerNIC());
        stm.setObject(2, customer.getName());
        stm.setObject(3, customer.getMobileNumber());

        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        PreparedStatement stm=DbConnection.getInstance().getConnection().prepareStatement("UPDATE Customer SET name=?,mobileNumber=? WHERE customerNIC=?");
        stm.setObject(1,customer.getName());
        stm.setObject(2,customer.getMobileNumber());
        stm.setObject(3,customer.getCustomerNIC());

        return stm.executeUpdate()>0;
    }

    @Override
    public boolean deleteCustomer(String name) throws SQLException, ClassNotFoundException {
       if (DbConnection.getInstance().getConnection().prepareStatement("DELETE  FROM Customer WHERE name='" + name + "'").executeUpdate()>0){
           return true;
       }else{
           return false;
       }
    }

    @Override
    public List<Customer> searchCustomer(String value) throws SQLException, ClassNotFoundException {
        Connection con=DbConnection.getInstance().getConnection();

        PreparedStatement stm=con.prepareStatement("SELECT * FROM Customer WHERE name LIKE '%" + value + "%'");
        ResultSet rst=stm.executeQuery();

        List<Customer>customers=new ArrayList<>();

        while (rst.next()){
            customers.add(new Customer(
                    rst.getString("CustomerNIC"),
                    rst.getString("Name"),
                    rst.getString("MobileNumber")
            ));
        }
        return customers;
    }


    @Override
    public ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException {
        PreparedStatement stm=DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer");
        ResultSet  rst=stm.executeQuery();
        ArrayList<Customer>customers=new ArrayList<>();

            while (rst.next()){
                customers.add(new Customer(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3)
                ));
            }
            return customers;
    }

    @Override
    public String getProductNameByID(int id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm=DbConnection.getInstance().getConnection().prepareStatement("select productName From Product WHERE productId ='"+id+"'");
        ResultSet rst=stm.executeQuery();
        String productName="";
        while (rst.next()){

            productName=rst.getString(1);
        }
        return productName;
    }

    @Override
    public Customer getCustomer(String customerNIC) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Customer WHERE customerNIC=?");
        stm.setObject(1, customerNIC);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );

        } else {
            return null;
        }
    }
}
