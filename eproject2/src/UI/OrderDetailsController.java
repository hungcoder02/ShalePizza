/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Data.Food;
import Data.ShoppingCart;
import Data.ShoppingCartDAOimpl;
import Data.ShoppingCartDOB;
import static UI.ShoppingController.Orders;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Trang Tran
 */
public class OrderDetailsController {

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<ShoppingCart> tvOrder;

    @FXML
    private TableColumn<ShoppingCart, Integer> tcName;

    @FXML
    private TableColumn<ShoppingCart, Integer> tcQuantity;

    @FXML
    private TableColumn<ShoppingCart, String> tcPrice;

    @FXML
    private TableColumn<ShoppingCart, String> tcDate;
    @FXML
    private GridPane Gird;

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
    void ClickReset(ActionEvent event) throws IOException {
        Navigator.getInstance().goToOrderDetails();
    }

    @FXML
    void ClickSearch(ActionEvent event) {

    }

    @FXML
    void ClicktvCart(MouseEvent event) throws IOException {
        Orders.clear();

        Gird.getChildren().clear();
        int column = 0, row = 1;
        ShoppingCart ShoppingCartID = tvOrder.getSelectionModel().getSelectedItem();
        Orders = shoppingDAO.selectCart(ShoppingCartID.getShoppingId());
        for (Food food : Orders) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/UI/Card.fxml"));

            AnchorPane anchorPane = fxmlLoader.load();
            CardController card = fxmlLoader.getController();

            card.setData(food);
            if (column == 1) {
                column = 0;
                row++;
            }
            Gird.add(anchorPane, column++, row);
            GridPane.setMargin(anchorPane, new Insets(10));

        }

    }

    @FXML
    void ClickStaff(ActionEvent event) throws IOException {
        Navigator.getInstance().goToStaff();
    }
    private ShoppingCartDOB shoppingDAO = new ShoppingCartDAOimpl();

    public void initialize() {
        tvOrder.setItems(shoppingDAO.selectAll());
        tcDate.setCellValueFactory((Shopping) -> {
            return Shopping.getValue().getDateProperty();
        });
        tcName.setCellValueFactory((Shopping) -> {
            return Shopping.getValue().getShoppingIdProperty();
        });
        tcPrice.setCellValueFactory((Shopping) -> {
            return Shopping.getValue().getPriceProperty();
        });
        tcQuantity.setCellValueFactory((Shopping) -> {
            return Shopping.getValue().getQuantityProperty();
        });

    }

}
