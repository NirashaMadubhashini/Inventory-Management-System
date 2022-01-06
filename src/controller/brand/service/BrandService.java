package controller.brand.service;


import model.Brand;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BrandService {
    boolean saveBrand(String BrandCategoryName,Brand b) throws SQLException, ClassNotFoundException;
    boolean updateBrand(Brand b) throws SQLException, ClassNotFoundException;
    boolean deleteBrand(String name) throws SQLException, ClassNotFoundException;
    List<Brand> searchBrand(String value) throws SQLException, ClassNotFoundException;
    ArrayList<Brand> getAllBrands() throws SQLException, ClassNotFoundException;
    int getCategoryIdByName(String name)throws SQLException,ClassNotFoundException;
    String getCategoryNameByID(int id)throws SQLException,ClassNotFoundException;

}
