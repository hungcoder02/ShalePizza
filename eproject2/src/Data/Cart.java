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
import javafx.scene.image.ImageView;

/**
 *
 * @author Trang Tran
 */
public class Cart {

    private StringProperty Name, Price, FoodIMG;
    private ObjectProperty<Integer> Quantity;

    public Cart() {
        Quantity = new SimpleObjectProperty<>(null);
        Name = new SimpleStringProperty();
        Price = new SimpleStringProperty();
        FoodIMG = new SimpleStringProperty();
    }

    public Integer getQuantity() {
        return Quantity.get();
    }

    public void setQuantity(Integer Quantity) {
        this.Quantity.set(Quantity);
    }
    
    public String getName() {
        return Name.get();
    }

    public String getPrice() {
        return Price.get();
    }

    public String getFoodIMG() {
        return FoodIMG.get();
    }

    public void setName(String Name) {
        this.Name.set(Name);
    }

    public void setPrice(String Price) {
        this.Price.set(Price);
    }

    public void setFoodIMG(String FoodIMG) {
        this.FoodIMG.set(FoodIMG);
    }
    
    public ObjectProperty<Integer> getQuantityPropety(){
        return this.Quantity;
    }
    
    public StringProperty getNameProperty() {
        return this.Name;
    }

    public StringProperty getPriceProperty() {
        return this.Price;
    }

    public StringProperty getFoodIMGProperty() {
        return this.FoodIMG;
    }
}
