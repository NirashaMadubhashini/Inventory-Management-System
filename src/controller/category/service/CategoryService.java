package controller.category.service;

import model.Category;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CategoryService {
     boolean saveCategory(Category c) throws SQLException, ClassNotFoundException;
     boolean updateCategory(Category c) throws SQLException, ClassNotFoundException;
     boolean deleteCategory(String name) throws SQLException, ClassNotFoundException;
     List<Category> searchCategory(String value) throws SQLException, ClassNotFoundException;
     ArrayList<Category> getAllCategories() throws SQLException, ClassNotFoundException;

}
