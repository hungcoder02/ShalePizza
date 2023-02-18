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
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Trang Tran
 */
public class CartDAOimpl implements CartDOB{

    public DbType database = DbType.MYSQL;
    
    @Override
    public int insert(int UserClerk) {
        int newKey = 0;
        String sqlshopping = "INSERT into Shopping (Username_Clerk) ";
        sqlshopping += "VALUES (?)";
        ResultSet key = null;
        try (
                Connection conn = DbFactory.getConnection(database);
                PreparedStatement stmt = conn.prepareStatement(sqlshopping, Statement.RETURN_GENERATED_KEYS);) {
                stmt.setInt(1, UserClerk);
            int rowInserted = stmt.executeUpdate();
            if (rowInserted == 1) {
                key = stmt.getGeneratedKeys();
                key.next();
                newKey = key.getInt(1);
            } else {
                System.err.println("No Shopping Cart inserted");
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            try {
                if (key != null) {
                    key.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
        return newKey;
    }

    @Override
    public void insertCart(int Quantity, int ShoppingID, int FoodID) {
        String sqlshopping = "INSERT into Cart (Quantity,ShoppingID,FoodID) ";
        sqlshopping += "VALUES (?, ?, ?)";
        ResultSet key = null;
        try (
                Connection conn = DbFactory.getConnection(database);
                PreparedStatement stmt = conn.prepareStatement(sqlshopping, Statement.RETURN_GENERATED_KEYS);) {

            stmt.setInt(1, Quantity);
            stmt.setInt(2, ShoppingID);
            stmt.setInt(3, FoodID);
            int rowInserted = stmt.executeUpdate();
            if (rowInserted == 1) {
                key = stmt.getGeneratedKeys();
                key.next();
                int newKey = key.getInt(1);
                stmt.setInt(1, newKey);
            } else {
                System.err.println("No Shopping Cart inserted");
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            try {
                if (key != null) {
                    key.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }
}
