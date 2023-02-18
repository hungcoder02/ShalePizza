/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DBConnect.DbFactory;
import DBConnect.DbType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Trang Tran
 */
public class AdminDAOimpl implements AdminDOB {

    public DbType database = DbType.MYSQL;

    @Override
    public ObservableList<Admin> selectAll() {
        ObservableList<Admin> Admins = FXCollections.observableArrayList();
        try (Connection conn = DbFactory.getConnection(database);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Manager");) {
            while (rs.next()) {
                Admin a = new Admin();
                a.setUsername(rs.getString("Username"));
                a.setEmail(rs.getString("Email"));
                a.setFullName(rs.getString("FullName"));
                a.setPhone(rs.getString("Phone"));
                a.setPassword(rs.getString("Password"));
                Admins.add(a);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return Admins;
    }

    @Override
    public boolean selectLogin(String Username) {
        String sql = "SELECT Password FROM Manager "
                + "WHERE Username = ?";
        try (
                Connection conn = DbFactory.getConnection(database);
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, Username);
            int rowLogin = stmt.executeUpdate();

            if (rowLogin == 1) {
                return true;
            } else {
                System.err.println("No Admin Login");
                return false;
            }

        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
}
