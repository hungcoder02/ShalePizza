/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Data.Cart;
import Data.CartDAOimpl;
import Data.CartDOB;
import Data.Food;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Trang Tran
 */
public class ShoppingController {

    CartDOB CartDAO = new CartDAOimpl();

    public static ObservableList<Food> Orders = FXCollections.observableArrayList();
    int column = 0, row = 1;

    @FXML
    private Label txtSp;

    @FXML
    private Label txtPrice;

    @FXML
    private Label txtIntOrder;

    @FXML
    private GridPane Gird;

    @FXML
    void ClickShopping(ActionEvent event) throws IOException {
        Navigator.getInstance().goToShopping();
    }

    @FXML
    void ClickLogout(ActionEvent event) throws IOException {
        Navigator.getInstance().goToLogin(null);
    }

    @FXML
    void ClickBack(ActionEvent event) throws IOException {
        Navigator.getInstance().goToMenu();
    }

    @FXML
    void ClickPayment(ActionEvent event) throws IOException {
        if (Orders.size() > 0) {
            int ShoppingCart = CartDAO.insert(1);
            for (Food carts : Orders) {
                CartDAO.insertCart(carts.getQuantity(), ShoppingCart, carts.getFoodID());
            }
            Orders.clear();
            Navigator.getInstance().goToShopping();
        }
    }

    public void initialize() throws IOException {
        float sumPrice = 0;
        int sumQuantity = 0;
        for (Food sums : Orders) {
            sumPrice += Float.valueOf(sums.getPrice().substring(1)) * sums.getQuantity();
            sumQuantity += sums.getQuantity();
        }
        txtPrice.setText("$" + String.valueOf(sumPrice));
        txtSp.setText(String.valueOf(sumQuantity));
        Orders.addListener(new ListChangeListener<Food>() {
            @Override
            public void onChanged(javafx.collections.ListChangeListener.Change<? extends Food> c) {
                float sumPrice = 0;
                int sumQuantity = 0;
                if (c.next()) {
                    for (Food sums : Orders) {
                        sumPrice += Float.valueOf(sums.getPrice().substring(1)) * sums.getQuantity();
                        sumQuantity += sums.getQuantity();
                    }
                }
                txtPrice.setText("$" + String.valueOf(sumPrice));
                txtSp.setText(String.valueOf(sumQuantity));
            }
        });

        if (Orders.size() > 0) {
            txtIntOrder.setText(String.valueOf(Orders.size()));
            txtIntOrder.setStyle("-fx-background-color: red" + ";\n"
                    + "-fx-background-radius: 30;");
        }

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
}
