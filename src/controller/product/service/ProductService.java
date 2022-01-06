package controller.product.service;

import model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ProductService {
    boolean saveProduct(String ProductBrandName,Product p) throws SQLException, ClassNotFoundException;
    boolean updateProduct(Product p) throws SQLException, ClassNotFoundException;
    boolean deleteProduct(String name) throws SQLException, ClassNotFoundException;
    List<Product> searchProduct(String value) throws SQLException, ClassNotFoundException;
    ArrayList<Product> getAllProducts() throws SQLException, ClassNotFoundException;
    int getBrandIdByName(String name)throws SQLException,ClassNotFoundException;
    String getBrandNameByID(int id)throws SQLException,ClassNotFoundException;
    public Product getProductById(String Name) throws SQLException, ClassNotFoundException;
}
