package controller.brand;

import controller.brand.service.BrandService;
import db.DbConnection;
import model.Brand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandController implements BrandService {
 public List<String>getBrandNames() throws SQLException, ClassNotFoundException {
     ResultSet rst=DbConnection.getInstance().getConnection().prepareStatement(
             "SELECT  brandName FROM  Brand").executeQuery();
     List<String>names=new ArrayList<>();
     while (rst.next()){
         names.add(
                 rst.getString(1)
         );
     }
     return names;
 }

    @Override
    public boolean saveBrand(String BrandCategoryName,Brand b) throws SQLException, ClassNotFoundException {

        int categoryId = getCategoryIdByName(BrandCategoryName);
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Brand VALUES(?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, null);
        stm.setObject(2, categoryId);
        stm.setObject(3, b.getBrandName());
        stm.setObject(4, b.getDate());
        stm.setObject(5, b.getTime());

        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean updateBrand(Brand b) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Brand SET brandName=? WHERE brandId=?");
        stm.setObject(1,b.getBrandName());
        stm.setObject(2, b.getBrandId());

        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean deleteBrand(String name) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Brand WHERE brandName='" + name + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Brand> searchBrand(String value) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();

        PreparedStatement stm = con.prepareStatement("SELECT  * FROM Brand WHERE brandName LIKE '%"+value+"%'");
        ResultSet rst = stm.executeQuery();

        List<Brand> brands = new ArrayList<>();

        while (rst.next()) {
            brands.add(new Brand(
                    rst.getInt("brandId"),
                    rst.getInt("brandCategoryId"),
                    rst.getString("brandName"),
                    rst.getString("date"),
                    rst.getString("time")
            ));
        }
        return brands;
    }


    @Override
    public ArrayList<Brand> getAllBrands() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Brand");
        ResultSet rst = stm.executeQuery();
        ArrayList<Brand> brands = new ArrayList<>();
        while (rst.next()) {
            brands.add(new Brand(
                    rst.getInt(1),
                    rst.getInt(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)

            ));
        }
        return brands;
    }


    @Override
    public int getCategoryIdByName(String name) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("select CategoryId From Category Where CategoryName = '" + name + "'");
        ResultSet rst = stm.executeQuery();
        int categoryId = 0;
        while (rst.next()) {

            categoryId = rst.getInt(1);
        }
        return categoryId;
    }

    @Override
    public String getCategoryNameByID(int id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("select CategoryName From Category Where CategoryId = '" + id + "'");
        ResultSet rst = stm.executeQuery();
        String categoryName = "";
        while (rst.next()) {

            categoryName = rst.getString(1);
        }
        return categoryName;
    }


}
