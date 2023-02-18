/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DBConnect.DbFactory;
import DBConnect.DbType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Trang Tran
 */
public class ShoppingCartDAOimpl implements ShoppingCartDOB {
    
    public DbType database = DbType.MYSQL;
    
    @Override
    public ObservableList<ShoppingCart> selectAll() {
        String sql = "SELECT SUM(c.Quantity)AS Quantity,s.ShoppingID,s.Created,SUM(CAST(TRIM(LEADING '$' FROM f.Price) AS FLOAT))AS Price FROM cart AS c "
                + "INNER JOIN shopping AS s ON c.ShoppingID = s.ShoppingID "
                + "INNER JOIN food AS f ON f.FoodID = c.FoodID "
                + "GROUP BY s.ShoppingID ";
        ObservableList<ShoppingCart> ShoppingCarts = FXCollections.observableArrayList();
        
        try (Connection conn = DbFactory.getConnection(database);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                ShoppingCart a = new ShoppingCart();
                a.setShoppingId(rs.getInt("ShoppingID"));
                a.setPrice(rs.getString("Price"));                
                a.setDate(rs.getString("Created"));
                a.setQuantity(rs.getInt("Quantity"));
                ShoppingCarts.add(a);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return ShoppingCarts;
    }
    
    @Override
    public ObservableList<Food> selectCart(int ShoppingID) {
        String sql = "SELECT c.Quantity,f.Name,CAST(TRIM(LEADING '$' FROM f.Price) AS FLOAT)*c.Quantity AS Price,f.FoodIMG FROM cart AS c "
                + "INNER JOIN food AS f ON f.FoodID = c.FoodID "
                + "WHERE ShoppingID =" + ShoppingID;
        ObservableList<Food> Food = FXCollections.observableArrayList();
        try (Connection conn = DbFactory.getConnection(database);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                Food a = new Food();
                a.setName(rs.getString("Name"));
                a.setFoodIMG(rs.getString("FoodIMG"));
                a.setQuantity(rs.getInt("Quantity"));
                String price  = rs.getString("Price");
                a.setPrice("$"+price);
                Food.add(a);
            }
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return Food;
    }
    
}
