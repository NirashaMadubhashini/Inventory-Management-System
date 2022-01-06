package controller.product;

import controller.product.service.ProductService;
import db.DbConnection;
import model.Product;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductController implements ProductService {

    public List<String> getAllProductNames() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT productName FROM Product").executeQuery();
        List<String> names= new ArrayList<>();
        while (rst.next()){
            names.add(
                    rst.getString(1)
            );
        }
        return names;
    }

    @Override
    public boolean saveProduct(String ProductBrandName, Product p) throws SQLException, ClassNotFoundException {
        int brandId = getBrandIdByName(ProductBrandName);
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Product Values(?,?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, null);
        stm.setObject(2, p.getSerialNumber());
        stm.setObject(3, brandId);
        stm.setObject(4, p.getProductName());
        stm.setObject(5, p.getQty());
        stm.setObject(6, p.getPrice());
        stm.setObject(7, p.getDate());
        stm.setObject(8, p.getTime());

        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean updateProduct(Product p) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE  Product SET productName=?,qty=?,price=? WHERE productId=?");
        stm.setObject(1, p.getProductName());
        stm.setObject(2, p.getQty());
        stm.setObject(3, p.getPrice());
        stm.setObject(4, p.getProductId());

        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean deleteProduct(String name) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Product WHERE productName = '" + name + "'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Product> searchProduct(String value) throws SQLException, ClassNotFoundException {
        Connection con=DbConnection.getInstance().getConnection();

        PreparedStatement stm=con.prepareStatement("SELECT  * FROM Product WHERE productName LIKE '%" + value + "%'");
        ResultSet rst=stm.executeQuery();

        List<Product>products=new ArrayList<>();

        while (rst.next()){
            products.add(new Product(
                    rst.getInt("productId"),
                    rst.getString("serialNumber"),
                    rst.getInt("brandId"),
                    rst.getString("productName"),
                    rst.getInt("qty"),
                    rst.getDouble("price"),
                    rst.getString("date"),
                    rst.getString("time")

            ));
        }
        return products;
    }

    @Override
    public ArrayList<Product> getAllProducts() throws SQLException, ClassNotFoundException {
        PreparedStatement stm=DbConnection.getInstance().getConnection().prepareStatement("SELECT  * FROM Product");
        ResultSet rst=stm.executeQuery();
        ArrayList<Product>products=new ArrayList<>();
        while (rst.next()){
            products.add(new Product(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getInt(5),
                    rst.getDouble(6),
                    rst.getString(7),
                    rst.getString(8)

            ));
        }
        return products;
    }

    @Override
    public int getBrandIdByName(String name) throws SQLException, ClassNotFoundException {
       PreparedStatement stm=DbConnection.getInstance().getConnection().prepareStatement("select  BrandId From Brand WHERE BrandName= '"+name+"'");
       ResultSet rst=stm.executeQuery();
       int brandId=0;
       while (rst.next()){

           brandId=rst.getInt(1);
       }
       return brandId;
    }

    @Override
    public String getBrandNameByID(int id) throws SQLException, ClassNotFoundException {
       PreparedStatement stm=DbConnection.getInstance().getConnection().prepareStatement("select BrandName From Brand WHERE BrandId ='"+id+"'");
       ResultSet rst=stm.executeQuery();
       String brandName="";
       while (rst.next()){

           brandName=rst.getString(1);
       }
       return brandName;
    }

    @Override
    public Product getProductById(String productName) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT productId,qty, price FROM product WHERE productName=?");
        stm.setObject(1, productName);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Product(
                    rst.getInt(1),
                    rst.getInt(2),
                    rst.getDouble(3)
            );

        } else {
            return null;
        }
    }

    }
