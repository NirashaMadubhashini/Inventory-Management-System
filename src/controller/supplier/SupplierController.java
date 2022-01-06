package controller.supplier;

import controller.supplier.service.SupplierService;
import db.DbConnection;
import model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierController implements SupplierService {

    public List<String> getSupplierNICs() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Supplier").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }

    @Override
    public boolean saveSupplier(Supplier s) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Supplier VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, s.getSupplierNIC());
        stm.setObject(2, s.getSupplierName());
        stm.setObject(3, s.getEmail());
        stm.setObject(4, s.getDescription());

        return stm.executeUpdate() > 0;

    }

    @Override
    public boolean updateSupplier(Supplier s) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Supplier SET  supplierName=?,email=?,description=? WHERE supplierNIC=?");
        stm.setObject(1, s.getSupplierName());
        stm.setObject(2, s.getEmail());
        stm.setObject(3, s.getDescription());
        stm.setObject(4, s.getSupplierNIC());

        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean deleteSupplier(String name) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Supplier WHERE supplierName='" + name + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Supplier> searchSupplier(String value) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();

        PreparedStatement stm = con.prepareStatement("SELECT  * FROM Supplier WHERE supplierName LIKE '%"+value+"%'");
        ResultSet rst = stm.executeQuery();

        List<Supplier> suppliers = new ArrayList<>();

        while (rst.next()) {
            suppliers.add(new Supplier(
                    rst.getString("supplierNIC"),
                    rst.getString("supplierName"),
                    rst.getString("email"),
                    rst.getString("description")
            ));
        }
        return suppliers;
    }


    @Override
    public ArrayList<Supplier> getAllSuppliers() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Supplier");
        ResultSet rst = stm.executeQuery();
        ArrayList<Supplier> suppliers = new ArrayList<>();
        while (rst.next()) {
            suppliers.add(new Supplier(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return suppliers;
    }

    @Override
    public Supplier getSupplier(String supplierNIC) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Supplier WHERE supplierNIC=?");
        stm.setObject(1, supplierNIC);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Supplier(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );

        } else {
            return null;
        }
    }
}
