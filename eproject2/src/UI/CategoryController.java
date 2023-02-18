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
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author ducnc
 */
public class CategoryController {

    String strimg;
    public DbType database = DbType.MYSQL;

    private Category CategoryEdit = null;
    private CategoryDOB CategoryDAO = new CategoryDAOimpl();

    @FXML
    private TextField txtSearch;

    @FXML
    private Label lbUpdate;

    @FXML
    private JFXTextField txtCategoryname;

    @FXML
    private Label ErrName;

    @FXML
    private TableView<Category> tvCategory;

    @FXML
    private TableColumn<Category, String> tcCategoryname;
    
    @FXML
    void ClickCart(ActionEvent event) throws IOException {
        Navigator.getInstance().goToOrderDetails();
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
    void ClickReset(ActionEvent event) throws IOException {
        Navigator.getInstance().goToCategory();
    }     

    @FXML
    void ClickStaff(ActionEvent event) throws IOException {
        Navigator.getInstance().goToStaff();
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
    void ClickSearch(ActionEvent event) {
        String sql = "SELECT * FROM Category "
                + "WHERE CategoryName LIKE '%" + txtSearch.getText() + "%' ";

        ObservableList<Category> Categorys = FXCollections.observableArrayList();
        try (Connection conn = DbFactory.getConnection(database);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                Category a = new Category();
                a.setCategoryName(rs.getString("CategoryName"));
                a.setCategoryID(rs.getInt("CategoryID"));
                Categorys.add(a);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        tvCategory.setItems(Categorys);
        tcCategoryname.setCellValueFactory((Category) -> {
            return Category.getValue().getCategoryNameProperty();
        });

    }

    @FXML
    void ClickDelete(ActionEvent event) {
        Category selectedCategory = tvCategory.getSelectionModel().getSelectedItem();

        if (selectedCategory == null) {
            Warning();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Staff");
            alert.setHeaderText("Do you agree to delete Staff?");
            Optional<ButtonType> confirmationResponse
                    = alert.showAndWait();
            if (confirmationResponse.get() == ButtonType.OK) {
                boolean result = CategoryDAO.delete(selectedCategory);

                if (result) {
                    tvCategory.getItems().remove(selectedCategory);
                    System.out.println("A Staff is deleted");
                } else {
                    System.err.println("No Staff is deleted");
                }
            }
        }
    }

    @FXML
    void ClickSubmit(ActionEvent event) {
//        resetTxt();
        if (lbUpdate.getText().equals("Update")) {
            Category updateCategory = extractFromFields();
            updateCategory.setCategoryID(this.CategoryEdit.getCategoryID());
            boolean result = CategoryDAO.update(updateCategory);
            System.out.println(updateCategory.getCategoryID());
        } else {
            Category inserStaff = extractFromFields();
            inserStaff = CategoryDAO.insert(inserStaff);
        }

    }

    @FXML
    void ClickUpdate(ActionEvent event) {
        lbUpdate.setText("Update");

        Category updateCategory = tvCategory.getSelectionModel().getSelectedItem();
        txtCategoryname.setText(updateCategory.getCategoryName());

        CategoryEdit = updateCategory;
    }

//    
//      public void initialize() {
//        tvCategory.setItems(CategoryDAO.selectAll());
//          txtCategoryname.textProperty().addListener((observable, oldValue, newValue) -> {
//            ErrUser.setText("");
//            if (isNull(txtCategoryname.getText())) {
//                ErrUser.setText("Username not be empty!");
//            }
//        });
//    }
    public void initialize() {
        tvCategory.setItems(CategoryDAO.selectAll());
        tcCategoryname.setCellValueFactory((Category) -> {
            return Category.getValue().getCategoryNameProperty();
        });

    }

    private void resetTxt() {
        txtCategoryname.setText("");
    }

    private void Warning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Member Warning");
        alert.setHeaderText("You must choose to perform");
        alert.showAndWait();
    }

    private Category extractFromFields() {
        Category Category = new Category();
        Category.setCategoryName(txtCategoryname.getText());

        return Category;
    }

    public boolean isNull(String str) {
        if (str.equals("")) {
            return true;
        } else {
            return false;
        }
    }
}
