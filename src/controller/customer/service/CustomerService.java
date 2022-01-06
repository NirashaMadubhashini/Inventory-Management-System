package controller.customer.service;

import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerService {

    boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String name) throws SQLException, ClassNotFoundException;

    List<Customer> searchCustomer(String value) throws SQLException, ClassNotFoundException;

    ArrayList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException;

    String getProductNameByID(int id) throws SQLException, ClassNotFoundException;

     Customer getCustomer(String id) throws SQLException, ClassNotFoundException;


}
