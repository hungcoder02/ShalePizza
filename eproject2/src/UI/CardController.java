/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Data.Food;
import static UI.ShoppingController.Orders;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Trang Tran
 */
public class CardController {

    private String price;

    private Food orderNow;

    @FXML
    private ImageView Img;

    @FXML
    private Label Name;

    @FXML
    private Label Price;

    @FXML
    private Label Quantity;

    @FXML
    void ClickDelete(ActionEvent event) throws IOException {
        Orders.remove(orderNow);
        Navigator.getInstance().goToShopping();
    }

    @FXML
    void ClickMinus(ActionEvent event) {
        orderNow.setQuantity(orderNow.getQuantity() - 1);
        if (orderNow.getQuantity() <= 0) {
            orderNow.setQuantity(1);
        }
        Quantity.setText(String.valueOf(orderNow.getQuantity()));
        Price.setText("$" + String.valueOf(Double.parseDouble(orderNow.getPrice().substring(1)) * orderNow.getQuantity()));
        Food itemnull = orderNow;
        Orders.remove(orderNow);
        Orders.add(itemnull);
    }

    @FXML
    void ClickPlus(ActionEvent event) {
        orderNow.setQuantity(orderNow.getQuantity() + 1);
        Quantity.setText(String.valueOf(orderNow.getQuantity()));
        Price.setText("$" + String.valueOf(Double.parseDouble(orderNow.getPrice().substring(1)) * orderNow.getQuantity()));
        Food itemnull = orderNow;
        Orders.remove(orderNow);
        Orders.add(itemnull);
    }

    public void setData(Food food) {
        this.orderNow = food;
        Image img = new Image("file:/D:/Java/eproject2/src/img/" + food.getFoodIMG());
        orderNow.setQuantity(food.getQuantity());
        Quantity.setText(String.valueOf(orderNow.getQuantity()));
        Img.setImage(img);
        Name.setText(food.getName());
        
        Price.setText(String.valueOf(Float.valueOf(food.getPrice().substring(1))*food.getQuantity()));
    }
}
