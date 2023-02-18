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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 *
 */
public class CategoryDAOimpl implements CategoryDOB{
    public DbType database = DbType.MYSQL;
    
    @Override
    public ObservableList<Category> selectAll() {
        ObservableList<Category> Category = FXCollections.observableArrayList();
        try (Connection conn = DbFactory.getConnection(database);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Category");) {
            while (rs.next()) {
                Category a = new Category();
                
                a.setCategoryID(rs.getInt("CategoryID"));
                a.setCategoryName(rs.getString("CategoryName"));
                Category.add(a);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return Category;
    }
 
    @Override
    public Category insert(Category newCategory) {
        String sql = "INSERT into Category (CategoryName) ";
        sql += "VALUES (?)";
        ResultSet key = null;
        try (
                Connection conn = DbFactory.getConnection(database);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, newCategory.getCategoryName());
           
            int rowInserted = stmt.executeUpdate();
            if (rowInserted == 1) {
                key = stmt.getGeneratedKeys();
                key.next();
                int newKey = key.getInt(1);
                newCategory.setCategoryID(newKey);
                return newCategory;
            } else {
                System.err.println("No Category inserted");
                return null;
            }
        } catch (Exception e) {
            System.err.println(e);
            return null;
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
//    
    @Override
    public boolean update(Category updateCategory) {
        String sql = "UPDATE Category SET "
                + "CategoryName = ? "
                + "WHERE CategoryID = ? ";
        try (
                Connection conn = DbFactory.getConnection(database);
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, updateCategory.getCategoryName());
            stmt.setInt(2, updateCategory.getCategoryID()); 

            int rowUpdated = stmt.executeUpdate();

            if (rowUpdated == 1) {
                return true;
            } else {
                System.err.println("No Category updated");
                return false;
            }
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
//      @Override
//    public boolean update(Category updateCategory) {
//        String sql = "UPDATE Category SET "
//               
//                + "CategoryName = ? "
//                + "WHERE CategoryID = ?";
//        try (
//                Connection conn = DbFactory.getConnection(database);
//                PreparedStatement stmt = conn.prepareStatement(sql);
//            ) {
//            
//          
//            stmt.setString(1, updateCategory.getCategoryName());
//            stmt.setInt(2, updateCategory.getCategoryID());      
//     
//            int rowUpdated = stmt.executeUpdate();
//
//            if (rowUpdated == 1) {
//                return true;
//            } else {
//                System.err.println("No Member updated");
//                return false;
//            }
//        } catch (Exception e) {
//            System.err.println(e);
//            return false;
//        }
//    }
    
    
    @Override
    public boolean delete(Category deleteCategory) {
        String sql = "DELETE FROM Category WHERE CategoryID = ?";
        try (
                Connection conn = DbFactory.getConnection(database);
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, deleteCategory.getCategoryID());
            int rowDeleted = stmt.executeUpdate();

            if (rowDeleted == 1) {
                return true;
            } else {
                System.err.println("No Category deleted");
                return false;
            }

        } catch (Exception e) {
            System.err.println(e);
            return false;
        }

    }
}
