package controller.supplier.service;

import model.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierService {
    boolean saveSupplier(Supplier s) throws SQLException, ClassNotFoundException;

    boolean updateSupplier(Supplier s) throws SQLException, ClassNotFoundException;

    boolean deleteSupplier(String name) throws SQLException, ClassNotFoundException;

    List<Supplier> searchSupplier(String value) throws SQLException, ClassNotFoundException;

    ArrayList<Supplier> getAllSuppliers() throws SQLException, ClassNotFoundException;

    Supplier getSupplier(String id) throws SQLException, ClassNotFoundException;
}
