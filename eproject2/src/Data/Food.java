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
public class Food {

    private ObjectProperty<Integer> FoodID, Quantity;
    private StringProperty CategoryID, Name, Price, FoodIMG;
    private ObjectProperty<ImageView> FoodImage;

    public Food() {
        FoodImage = new SimpleObjectProperty<>(null);
        FoodID = new SimpleObjectProperty<>(null);
        Quantity = new SimpleObjectProperty<>(null);

        CategoryID = new SimpleStringProperty();
        Name = new SimpleStringProperty();
        Price = new SimpleStringProperty();
        FoodIMG = new SimpleStringProperty();
    }

    public int getQuantity() {
        return Quantity.get();
    }

    public void setQuantity(Integer Quantity) {
        this.Quantity.set(Quantity);
    }

    public Integer getFoodID() {
        return FoodID.get();
    }

    public ImageView getFoodImage() {
        return FoodImage.get();
    }

    public String getCategoryID() {
        return CategoryID.get();
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

    public void setFoodID(Integer FoodID) {
        this.FoodID.set(FoodID);
    }

    public void setFoodImage(ImageView FoodImage) {
        this.FoodImage.set(FoodImage);
    }

    public void setCategoryID(String CategoryID) {
        this.CategoryID.set(CategoryID);
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

    public ObjectProperty<Integer> getFoodIDProperty() {
        return this.FoodID;
    }
    public ObjectProperty<Integer> getQuantityProperty() {
        return this.Quantity;
    }

    public ObjectProperty<ImageView> getFoodImageProperty() {
        return this.FoodImage;
    }

    public StringProperty getCategoryIDProperty() {
        return this.CategoryID;
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
