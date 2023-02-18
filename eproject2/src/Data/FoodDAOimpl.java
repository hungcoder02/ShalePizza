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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Trang /
 */
public class FoodDAOimpl implements FoodDOB {

    public DbType database = DbType.MYSQL;

    @Override
    public ObservableList<Food> selectAll() {
        ObservableList<Food> Food = FXCollections.observableArrayList();
        String sql = "SELECT FoodID,Name ,Price,FoodIMG, category.CategoryName FROM Food "
                + "INNER JOIN Category ON Food.CategoryID=Category.CategoryID";
        try (Connection conn = DbFactory.getConnection(database);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                Food a = new Food();
                a.setFoodID(rs.getInt("FoodID"));
                a.setCategoryID(rs.getString("CategoryName"));
                a.setName(rs.getString("Name"));
                a.setFoodIMG(rs.getString("FoodIMG"));
                Image img = new Image("file:/D:/Java/eproject2/src/img/" + rs.getString("FoodIMG"));
                ImageView imgView = new ImageView(img);
                imgView.setFitHeight(80);
                imgView.setFitWidth(130);
                a.setFoodImage(imgView);
                a.setPrice(rs.getString("Price"));
                Food.add(a);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return Food;
    }

    @Override
    public Food insert(Food newFood) {
        String sql = "INSERT into Food (CategoryID, Name,Price,FoodIMG) ";
        sql += "VALUES (?, ?, ?, ?)";
        ResultSet key = null;
        try (
                Connection conn = DbFactory.getConnection(database);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            int category = selectCantegoryID(newFood.getCategoryID());
            stmt.setInt(1, category);
            stmt.setString(2, newFood.getName());
            stmt.setString(3, newFood.getPrice());
            stmt.setString(4, newFood.getFoodIMG());
            int rowInserted = stmt.executeUpdate();
            if (rowInserted == 1) {
                key = stmt.getGeneratedKeys();
                key.next();
                int newKey = key.getInt(1);
                newFood.setFoodID(newKey);
                return newFood;
            } else {
                System.err.println("No Food inserted");
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

    @Override
    public boolean update(Food updateFood) {
        String sql = "UPDATE Food SET "
                + "Name = ?, "
                + "Price = ?, "
                + "FoodIMG = ?, "
                + "CategoryID = ? "
                + "WHERE FoodID = ?";
        try (
                Connection conn = DbFactory.getConnection(database);
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, updateFood.getName());
            stmt.setString(2, updateFood.getPrice());
            stmt.setString(3, updateFood.getFoodIMG());
            int category = selectCantegoryID(updateFood.getCategoryID());

            stmt.setInt(4, category);
            stmt.setInt(5, updateFood.getFoodID());

            int rowUpdated = stmt.executeUpdate();

            if (rowUpdated == 1) {
                return true;
            } else {
                System.err.println("No Food updated");
                return false;
            }
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    @Override
    public boolean delete(Food deleteFood) {
        String sql = "DELETE FROM Food WHERE FoodID = ?";
        try (
                Connection conn = DbFactory.getConnection(database);
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, deleteFood.getFoodID());
            int rowDeleted = stmt.executeUpdate();

            if (rowDeleted == 1) {
                return true;
            } else {
                System.err.println("No Food deleted");
                return false;
            }

        } catch (Exception e) {
            System.err.println(e);
            return false;
        }

    }

    public int selectCantegoryID(String Category) {
        String sql = "SELECT CategoryID FROM category "
                + "WHERE CategoryName =" + "'" + Category + "'";
        try (
                Connection conn = DbFactory.getConnection(database);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);) {
            int CategoryID = 0;
            while (rs.next()) {
                CategoryID = rs.getInt("CategoryID");
            }
            return CategoryID;
        } catch (Exception e) {
            System.err.println(e);
            return 0;
        }
    }

    @Override
    public ObservableList<Food> selectAll(int from, int to) {
        ObservableList<Food> Food = FXCollections.observableArrayList();
        String sql = "SELECT FoodID,Name ,Price,FoodIMG, category.CategoryName FROM Food "
                + "INNER JOIN Category ON Food.CategoryID=Category.CategoryID "
                + "LIMIT " + from + "," + to;

        try (Connection conn = DbFactory.getConnection(database);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                Food a = new Food();
                a.setFoodID(rs.getInt("FoodID"));
                a.setCategoryID(rs.getString("CategoryName"));
                a.setName(rs.getString("Name"));
                a.setFoodIMG(rs.getString("FoodIMG"));
                Image img = new Image("file:/D:/Java/eproject2/src/img/" + rs.getString("FoodIMG"));
                ImageView imgView = new ImageView(img);
                imgView.setFitHeight(80);
                imgView.setFitWidth(130);
                a.setFoodImage(imgView);
                a.setPrice(rs.getString("Price"));
                Food.add(a);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return Food;
    }

    @Override
    public ObservableList<Food> selectAllCategoryName(String Categoryname) {
        ObservableList<Food> Food = FXCollections.observableArrayList();
        String sql = "SELECT FoodID,Name ,Price,FoodIMG, category.CategoryName FROM Food "
                + "INNER JOIN Category ON Food.CategoryID=Category.CategoryID "
                + "WHERE Category.CategoryName = '" + Categoryname + "' ";

        try (Connection conn = DbFactory.getConnection(database);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                Food a = new Food();
                a.setFoodID(rs.getInt("FoodID"));
                a.setCategoryID(rs.getString("CategoryName"));
                a.setName(rs.getString("Name"));
                a.setFoodIMG(rs.getString("FoodIMG"));
                Image img = new Image("file:/D:/Java/eproject2/src/img/" + rs.getString("FoodIMG"));
                ImageView imgView = new ImageView(img);
                imgView.setFitHeight(80);
                imgView.setFitWidth(130);
                a.setFoodImage(imgView);
                a.setPrice(rs.getString("Price"));
                Food.add(a);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return Food;
    }

    @Override
    public int countFood() {
        int count = 0;
        try (Connection conn = DbFactory.getConnection(database);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT count(*) FROM Food");) {
            while (rs.next()) {
                count = rs.getInt("count(*)");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return count;
    }
    @Override
    public int countFoodCategory(String CategoryName) {
         String sql = "SELECT count(*)  FROM Food AS f"
                + " INNER JOIN Category ON f.CategoryID=category.CategoryID "
                + " WHERE category.CategoryName = '"+CategoryName+"' ";
        int count = 0;
        try (Connection conn = DbFactory.getConnection(database);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                count = rs.getInt("count(*)");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return count;
    }

    @Override
    public ObservableList<Food> selectAllCategoryName(String CategoryName, int from, int to) {
ObservableList<Food> Food = FXCollections.observableArrayList();
        String sql = "SELECT FoodID,Name ,Price,FoodIMG, category.CategoryName FROM Food "
                + "INNER JOIN Category ON Food.CategoryID=Category.CategoryID "
                + "WHERE category.CategoryName ='" +CategoryName + "' "
                + "LIMIT " + from + "," + to;

        try (Connection conn = DbFactory.getConnection(database);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                Food a = new Food();
                a.setFoodID(rs.getInt("FoodID"));
                a.setCategoryID(rs.getString("CategoryName"));
                a.setName(rs.getString("Name"));
                a.setFoodIMG(rs.getString("FoodIMG"));
                Image img = new Image("file:/D:/Java/eproject2/src/img/" + rs.getString("FoodIMG"));
                ImageView imgView = new ImageView(img);
                imgView.setFitHeight(80);
                imgView.setFitWidth(130);
                a.setFoodImage(imgView);
                a.setPrice(rs.getString("Price"));
                Food.add(a);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return Food;
     }

}
