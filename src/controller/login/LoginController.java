package controller.login;

import db.DbConnection;
import model.Customer;
import model.Login;
import sun.rmi.runtime.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginController {

    public List<Login> getAllLogin() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Login");
        ResultSet rst = stm.executeQuery();
        ArrayList<Login> logins = new ArrayList<>();

        while (rst.next()) {
            logins.add(new Login(
                    rst.getString(1),
                    rst.getString(2)
            ));
        }
        return logins;
    }

}
