package controller.category;

import controller.category.service.CategoryService;
import db.DbConnection;
import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryController implements CategoryService {

    public List<String> getCategoryNames() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT categoryName FROM Category").executeQuery();
        List<String> names = new ArrayList<>();
        while (rst.next()) {
            names.add(
                    rst.getString(1)
            );
        }
        return names;
    }



    @Override
    public boolean saveCategory(Category c) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Category VALUES(?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, null);
        stm.setObject(2, c.getCategoryName());
        stm.setObject(3, c.getDescription());
        stm.setObject(4, c.getDate());
        stm.setObject(5, c.getTime());

        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean updateCategory(Category c) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Category SET description=?,categoryName=? WHERE categoryId=? ");
        stm.setObject(1, c.getDescription());
        stm.setObject(2, c.getCategoryName());
        stm.setObject(3, c.getCategoryId());

        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean deleteCategory(String name) throws SQLException, ClassNotFoundException {

        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Category WHERE categoryName='" + name + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Category> searchCategory(String value) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();

        PreparedStatement stm = con.prepareStatement("SELECT  * FROM Category WHERE categoryName LIKE '%"+value+"%'");
        ResultSet rst = stm.executeQuery();

        List<Category> categories = new ArrayList<>();

        while (rst.next()) {
            categories.add(new Category(
                    rst.getInt("categoryId"),
                    rst.getString("categoryName"),
                    rst.getString("description"),
                    rst.getString("date"),
                    rst.getString("time")
            ));
        }
        return categories;
    }


    public  ArrayList<Category> getAllCategories() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Category");
        ResultSet rst = stm.executeQuery();
        ArrayList<Category> categories = new ArrayList<>();
        while (rst.next()) {
            categories.add(new Category(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)

            ));
        }
        return categories;
    }


}
