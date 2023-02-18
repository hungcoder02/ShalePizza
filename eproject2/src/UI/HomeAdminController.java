/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DBConnect.DbFactory;
import DBConnect.DbType;
import Data.Category;
import Data.CategoryDAOimpl;
import Data.CategoryDOB;
import Data.Food;
import Data.FoodDAOimpl;
import Data.FoodDOB;
import Data.Staff;
import Data.StaffDAOimpl;
import Data.StaffDOB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

/**
 *
 * @author Trang Tran
 */
public class HomeAdminController implements Initializable {

    public DbType database = DbType.MYSQL;

    private Staff staffEdit = null;
    private StaffDOB staffDAO = new StaffDAOimpl();
    private Category CategoryEdit = null;
    private CategoryDOB CategoryDAO = new CategoryDAOimpl();
    private Food foodEdit = null;
    private FoodDOB foodDAO = new FoodDAOimpl();

    @FXML
    private Label txtMember;

    @FXML
    private Label txtCategory;

    @FXML
    private Label txtFood;

    @FXML
    private Label txtCart;

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    void ClickCart(ActionEvent event) throws IOException {
        Navigator.getInstance().goToOrderDetails();
    }

    @FXML
    void ClickCategory(ActionEvent event) throws IOException {
        Navigator.getInstance().goToCategory();
    }

    @FXML
    void ClickFood(ActionEvent event) throws IOException {
        Navigator.getInstance().goToFood();
    }

    @FXML
    void ClickHome(ActionEvent event) throws IOException {
        Navigator.getInstance().goToHome();
    }

    @FXML
    void ClickLogout(ActionEvent event) throws IOException {
        Navigator.getInstance().goToLogin(null);
    }

    @FXML
    void ClickStaff(ActionEvent event) throws IOException {
        Navigator.getInstance().goToStaff();
    }

    public int countmember() {
        int count = 0;
        try (Connection conn = DbFactory.getConnection(database);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT COUNT(MemberID) AS Count FROM clerk ");) {
            while (rs.next()) {
                count = rs.getInt("Count");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return count;
    }

    public int countCategory() {
        int count = 0;
        try (Connection conn = DbFactory.getConnection(database);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT COUNT(CategoryID) AS Count FROM category ");) {
            while (rs.next()) {
                count = rs.getInt("Count");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return count;
    }

    public int countFood() {
        int count = 0;
        try (Connection conn = DbFactory.getConnection(database);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT COUNT(FoodID) AS Count FROM food ");) {
            while (rs.next()) {
                count = rs.getInt("Count");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return count;
    }

    public int countCart() {
        int count = 0;
        try (Connection conn = DbFactory.getConnection(database);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT COUNT(ShoppingID) AS Count FROM shopping ");) {
            while (rs.next()) {
                count = rs.getInt("Count");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return count;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtMember.setText(String.valueOf(countmember()));
        txtCart.setText(String.valueOf(countCart()));
        txtCategory.setText(String.valueOf(countCategory()));
        txtFood.setText(String.valueOf(countFood()));

        XYChart.Series<String, Number> pizzas = new XYChart.Series<>();
        XYChart.Data<String, Number> Jan = new XYChart.Data<>("Jan", 3000);
        XYChart.Data<String, Number> Feb = new XYChart.Data<>("Feb", 8000);
        XYChart.Data<String, Number> Mar = new XYChart.Data<>("Mar", 6000);
        XYChart.Data<String, Number> Apr = new XYChart.Data<>("Apr", 1000);
        XYChart.Data<String, Number> May = new XYChart.Data<>("May", 10000);
        XYChart.Data<String, Number> Jun = new XYChart.Data<>("Jun", 5000);
        XYChart.Data<String, Number> Jul = new XYChart.Data<>("Jul", 8000);
        XYChart.Data<String, Number> Aug = new XYChart.Data<>("Aug", 2000);
        XYChart.Data<String, Number> Sep = new XYChart.Data<>("Sep", 7000);
        pizzas.getData().addAll(Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep);
        pizzas.setName("Pizzas");
        lineChart.getData().add(pizzas);

        XYChart.Series<String, Number> desert = new XYChart.Series<>();
        XYChart.Data<String, Number> Jan1 = new XYChart.Data<>("Jan", 5000);
        XYChart.Data<String, Number> Feb1 = new XYChart.Data<>("Feb", 2000);
        XYChart.Data<String, Number> Mar1 = new XYChart.Data<>("Mar", 9000);
        XYChart.Data<String, Number> Apr1 = new XYChart.Data<>("Apr", 6000);
        XYChart.Data<String, Number> May1 = new XYChart.Data<>("May", 1000);
        XYChart.Data<String, Number> Jun1 = new XYChart.Data<>("Jun", 8000);
        XYChart.Data<String, Number> Jul1 = new XYChart.Data<>("Jul", 3000);
        XYChart.Data<String, Number> Aug1 = new XYChart.Data<>("Aug", 4000);
        XYChart.Data<String, Number> Sep1 = new XYChart.Data<>("Sep", 7000);
        desert.getData().addAll(Jan1, Feb1, Mar1, Apr1, May1, Jun1, Jul1, Aug1, Sep1);
        desert.setName("Desert");
        lineChart.getData().add(desert);
        
        XYChart.Series<String, Number> beverages = new XYChart.Series<>();
        XYChart.Data<String, Number> Jan2 = new XYChart.Data<>("Jan", 3000);
        XYChart.Data<String, Number> Feb2 = new XYChart.Data<>("Feb", 5000);
        XYChart.Data<String, Number> Mar2 = new XYChart.Data<>("Mar", 9000);
        XYChart.Data<String, Number> Apr2 = new XYChart.Data<>("Apr", 10000);
        XYChart.Data<String, Number> May2 = new XYChart.Data<>("May", 4000);
        XYChart.Data<String, Number> Jun2 = new XYChart.Data<>("Jun", 8000);
        XYChart.Data<String, Number> Jul2 = new XYChart.Data<>("Jul", 6000);
        XYChart.Data<String, Number> Aug2 = new XYChart.Data<>("Aug", 5000);
        XYChart.Data<String, Number> Sep2 = new XYChart.Data<>("Sep", 2000);
        beverages.getData().addAll(Jan2, Feb2, Mar2, Apr2, May2, Jun2, Jul2, Aug2, Sep2);
        beverages.setName("Beverages");
        lineChart.getData().add(beverages);
        
        XYChart.Series<String, Number> bread = new XYChart.Series<>();
        XYChart.Data<String, Number> Jan3 = new XYChart.Data<>("Jan", 7000);
        XYChart.Data<String, Number> Feb3 = new XYChart.Data<>("Feb", 4000);
        XYChart.Data<String, Number> Mar3 = new XYChart.Data<>("Mar", 8000);
        XYChart.Data<String, Number> Apr3 = new XYChart.Data<>("Apr", 6000);
        XYChart.Data<String, Number> May3 = new XYChart.Data<>("May", 9000);
        XYChart.Data<String, Number> Jun3 = new XYChart.Data<>("Jun", 2000);
        XYChart.Data<String, Number> Jul3 = new XYChart.Data<>("Jul", 1000);
        XYChart.Data<String, Number> Aug3 = new XYChart.Data<>("Aug", 5000);
        XYChart.Data<String, Number> Sep3 = new XYChart.Data<>("Sep", 4000);
        bread.getData().addAll(Jan3, Feb3, Mar3, Apr3, May3, Jun3, Jul3, Aug3, Sep3);
        bread.setName("Bread");
        lineChart.getData().add(bread);
    }

}
