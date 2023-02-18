/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DBConnect.DbFactory;
import DBConnect.DbType;
import Data.Food;
import Data.FoodDAOimpl;
import Data.FoodDOB;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Trang Tran
 */
public class FoodController {

    String Image;

    public DbType database = DbType.MYSQL;

    private Food foodEdit = null;
    private FoodDOB foodDAO = new FoodDAOimpl();
    
    private String strIMG;
    List<String> categoryname = cbCategoryName();

    @FXML
    private Label lbUpdate;

    @FXML
    private ComboBox<String> cbCategoryF;

    @FXML
    private Label ErrCategoryF;

    @FXML
    private JFXTextField txtNameF;

    @FXML
    private Label ErrNameF;

    @FXML
    private JFXTextField txtPriceF;

    @FXML
    private Label ErrPriceF;

    @FXML
    private TextField txtSearch;

    @FXML
    private ImageView foodIMG;

    @FXML
    private ImageView tcIMG;

    @FXML
    private TableView<Food> tvFood;

    @FXML
    private TableColumn<Food, String> tcCategory;

    @FXML
    private TableColumn<Food, String> tcNameF;

    @FXML
    private TableColumn<Food, String> tcPrice;

    @FXML
    void ClickCart(ActionEvent event) throws IOException {
        Navigator.getInstance().goToOrderDetails();
    }

    @FXML
    void ClickHome(ActionEvent event) throws IOException {
        Navigator.getInstance().goToHome();
    }

    @FXML
    void ClickFood(ActionEvent event) throws IOException {
        Navigator.getInstance().goToFood();
    }

    @FXML
    void ClickLogout(ActionEvent event) throws IOException {
        Navigator.getInstance().goToLogin(null);
    }

    @FXML
    void ClickReset(ActionEvent event) throws IOException {
        Navigator.getInstance().goToFood();
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
    void clickFile(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        String path = "D:\\Java\\eproject2\\src\\img\\";
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            Files.copy(file.toPath(), (new File(path + file.getName())).toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        strIMG = file.getName();
        Image img = new Image(file.toURI().toString());
        System.out.println(file.toURI().toString());
        foodIMG.setImage(img);

    }

    @FXML
    void ClickDelete(ActionEvent event) {
        Food selectedStaff = tvFood.getSelectionModel().getSelectedItem();

        if (selectedStaff == null) {
            Warning();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Food");
            alert.setHeaderText("Do you agree to delete Food?");
            Optional<ButtonType> confirmationResponse
                    = alert.showAndWait();
            if (confirmationResponse.get() == ButtonType.OK) {
                boolean result = foodDAO.delete(selectedStaff);

                if (result) {
                    tvFood.getItems().remove(selectedStaff);
                    System.out.println("A Food is deleted");
                } else {
                    System.err.println("No Food is deleted");
                }
            }
        }
    }

    @FXML
    void ClickIMG(MouseEvent event) {
        Food Imgchose = tvFood.getSelectionModel().getSelectedItem();
        Image img = new Image("file:/D:/Java/eproject2/src/img/" + Imgchose.getFoodIMG());
        tcIMG.setImage(img);
    }

    @FXML
    void ClickSearch(ActionEvent event) {
        String sql = "SELECT FoodID,Name ,Price,FoodIMG, category.CategoryName FROM Food "
                + "INNER JOIN Category ON Food.CategoryID=Category.CategoryID "
                + "WHERE Name LIKE '%" + txtSearch.getText() + "%' "
                + "or Price LIKE '%" + txtSearch.getText() + "%' ";

        ObservableList<Food> Foods = FXCollections.observableArrayList();
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
                Foods.add(a);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        tvFood.setItems(Foods);
        tcCategory.setCellValueFactory((Food) -> {
            return Food.getValue().getCategoryIDProperty();
        });
        tcNameF.setCellValueFactory((Food) -> {
            return Food.getValue().getNameProperty();
        });
        tcPrice.setCellValueFactory((Food) -> {
            return Food.getValue().getPriceProperty();
        });
    }

    @FXML
    void ClickSubmitFood(ActionEvent event) {
        if (lbUpdate.getText().equals("Update")) {
            Food updateFood = extractFromFields();
            updateFood.setFoodID(this.foodEdit.getFoodID());
            boolean result = foodDAO.update(updateFood);

        } else {
            Food insertFood = extractFromFields();
            insertFood = foodDAO.insert(insertFood);
        }
    }

    @FXML
    void ClickUpdate(ActionEvent event) {
        lbUpdate.setText("Update");
        Food updateFood = tvFood.getSelectionModel().getSelectedItem();
        cbCategoryF.getSelectionModel().select(updateFood.getCategoryID());
        txtNameF.setText(updateFood.getName());
        txtPriceF.setText(updateFood.getPrice());
        this.foodEdit = updateFood;
        strIMG = updateFood.getFoodIMG();
        Image img = new Image("file:/D:/Java/eproject2/src/img/"+strIMG);
        foodIMG.setImage(img);

    }

    public void initialize() {
        cbCategoryF.getItems().removeAll(cbCategoryF.getItems());
        for (String str : categoryname) {
            cbCategoryF.getItems().addAll(str);
        }
        cbCategoryF.getSelectionModel().select(categoryname.get(0));
        tvFood.setItems(foodDAO.selectAll());
        tcCategory.setCellValueFactory((Food) -> {
            return Food.getValue().getCategoryIDProperty();
        });
        tcNameF.setCellValueFactory((Food) -> {
            return Food.getValue().getNameProperty();
        });
        tcPrice.setCellValueFactory((Food) -> {
            return Food.getValue().getPriceProperty();
        });

        txtNameF.textProperty().addListener((observable, oldValue, newValue) -> {
            ErrNameF.setText("");
            if (isNull(txtNameF.getText())) {
                ErrNameF.setText("Name Food Not be empty!");
            }
        });
        txtPriceF.textProperty().addListener((observable, oldValue, newValue) -> {
            ErrPriceF.setText("");
            if (isNull(txtPriceF.getText())) {
                ErrPriceF.setText("Price Not be empty!");
            }
        });
    }

    private Food extractFromFields() {
        Food food = new Food();
        food.setCategoryID(cbCategoryF.getValue());
        food.setName(txtNameF.getText());
        food.setPrice(txtPriceF.getText());
        food.setFoodIMG(strIMG);

        return food;
    }

    public List<String> cbCategoryName() {
        List<String> c = new ArrayList<String>();
        try (Connection conn = DbFactory.getConnection(database);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT CategoryName FROM Category");) {
            while (rs.next()) {
                c.add(rs.getString("CategoryName"));
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return c;

    }

    private void Warning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Member Warning");
        alert.setHeaderText("You must choose to perform");
        alert.showAndWait();
    }

    public boolean isNull(String str) {
        if (str.equals("")) {
            return true;
        } else {
            return false;
        }
    }

}
