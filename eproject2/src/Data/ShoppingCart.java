/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Trang Tran
 */
public class ShoppingCart {

    private ObjectProperty<Integer> ShoppingId, Quantity;
    private StringProperty  Date, Price;

    public ShoppingCart() {
        ShoppingId = new SimpleObjectProperty<>(null);
        Quantity = new SimpleObjectProperty<>(null);
        Price = new SimpleStringProperty();
        Date = new SimpleStringProperty();
    }

    public void setShoppingId(Integer ShoppingId) {
        this.ShoppingId.set(ShoppingId);
    }

    public void setQuantity(Integer Quantity) {
        this.Quantity.set(Quantity);
    }

    public void setPrice(String Price) {
        this.Price.set(Price);
    }

    
    public void setDate(String Date) {
        this.Date.set(Date);
    }

    public ObjectProperty<Integer> getShoppingIdProperty() {
        return ShoppingId;
    }

    public ObjectProperty<Integer> getQuantityProperty() {
        return Quantity;
    }

    public StringProperty getPriceProperty() {
        return Price;
    }

  

    public StringProperty getDateProperty() {
        return Date;
    }

    public Integer getShoppingId() {
        return ShoppingId.get();
    }

    public Integer getQuantity() {
        return Quantity.get();
    }

    public String getPrice() {
        return Price.get();
    }


    public String getDate() {
        return Date.get();
    }
}
