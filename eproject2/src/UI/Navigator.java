/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Data.Admin;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Trang Tran
 */
public class Navigator {

    public static final String LOGIN_FXML = "Login.fxml";
    public static final String HOME_FXML = "HomeAdmin.fxml";
    public static final String CATEGORY_FXML = "Category.fxml";
    public static final String STAFF_FXML = "Staff.fxml";
    public static final String ORDER_FXML = "OrderDetails.fxml";
    public static final String FOOD_FXML = "Food.fxml";
    public static final String MENU_FXML = "Menu.fxml";
    public static final String SHOPPING_FXML = "Shopping.fxml";

    private FXMLLoader loader;
    private Stage stage = null;

    private static Navigator nav = null;

    private Navigator() {
    }

    public static Navigator getInstance() {
        if (nav == null) {
            nav = new Navigator();
        }

        return nav;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    private void goTo(String fxml) throws IOException {
        this.loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
    }

    public void goToLogin(Admin Login) throws IOException {
        this.goTo(LOGIN_FXML);
        LoginController ctrl = loader.getController();
        ctrl.initialize(Login);
    }
    
    public void goToHome() throws IOException{
        this.goTo(HOME_FXML);
    }
    
    public void goToCategory() throws IOException{
        this.goTo(CATEGORY_FXML);
    }
    
    public void goToStaff() throws IOException{
        this.goTo(STAFF_FXML);
    }
    
    public void goToOrderDetails() throws IOException{
        this.goTo(ORDER_FXML);
    }
    
    public void goToFood() throws IOException{
        this.goTo(FOOD_FXML);
    }
    
    public void goToMenu() throws IOException{
        this.goTo(MENU_FXML);
    }
    
    public void goToShopping() throws IOException{
        this.goTo(SHOPPING_FXML);
    }
}
