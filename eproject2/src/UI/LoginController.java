/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DBConnect.DbFactory;
import DBConnect.DbType;
import Data.Admin;
import Data.AdminDAOimpl;
import static UI.ShoppingController.Orders;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 *
 * @author Trang Tran
 */
public class LoginController {
    

    private Admin adminLogin = null;
    private AdminDAOimpl adminDAO = new AdminDAOimpl();
    public DbType database = DbType.MYSQL;

    @FXML
    private JFXTextField txtUser;

    @FXML
    private ComboBox<String> cbUserType;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXPasswordField txtPass;

    @FXML
    private Label ErrUser;

    @FXML
    private Label ErrPass;

    @FXML
    private Label ErrUserType;

    @FXML
    void clickLogin(ActionEvent event) throws IOException {
        ErrUserType.setText("");
        if(selectLogin(txtUser.getText())&&cbUserType.getValue().equals("Manager")){
            Navigator.getInstance().goToHome();
        }else if(selectLogin(txtUser.getText())&&cbUserType.getValue().equals("Clerk")){
            Navigator.getInstance().goToMenu();
        }else{
            ErrUserType.setText("User account or password incorrect");
        }  
    }

    void initialize(Admin Login) {
       Orders.clear();
        ErrUser.setText("");
        ErrPass.setText("");
        ErrUserType.setText("");
        this.adminLogin = Login;
        
        txtUser.textProperty().addListener((observable, oldValue, newValue) -> {
            ErrUser.setText("");
            if (isNull(txtUser.getText())) {
                ErrUser.setText("UserName is null!");
            }
        });
        
        txtPass.textProperty().addListener((observable, oldValue, newValue) -> {
            ErrPass.setText("");
            if (isNull(txtPass.getText())) {
                ErrPass.setText("Password is null!");
            }
        });
        
        cbUserType.getItems().removeAll(cbUserType.getItems());
        cbUserType.getItems().addAll("Manager", "Clerk");
        cbUserType.getSelectionModel().select("Clerk");
    }

    public boolean isNull(String str) {
        if (str.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean selectLogin(String Username) {
        String sql = "SELECT Password FROM Manager "
                + "WHERE UserName = " + "'" + Username + "'";

        try (
                Connection conn = DbFactory.getConnection(database);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);) 
        {
            String password = null;

            while (rs.next()) {
                password = rs.getString("PassWord");
            }

            if (txtPass.getText().equals(password)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

}
