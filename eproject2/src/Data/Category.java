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
public class Category {

    static void add(Category a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private ObjectProperty<Integer> CategoryID;
    private StringProperty CategoryName;
    
    public Category() {
        CategoryID = new SimpleObjectProperty<>(null);
        CategoryName = new SimpleStringProperty();
    }
    
    public Integer getCategoryID() {
        return CategoryID.get();
    }

    public String getCategoryName() {
        return CategoryName.get();
    }
    
    public void setCategoryID(Integer FoodID) {
        this.CategoryID.set(FoodID);
    }

    public void setCategoryName(String Name) {
        this.CategoryName.set(Name);
    }
    
    public ObjectProperty<Integer> getCategoryIDProperty() {
        return this.CategoryID;
    }

    public StringProperty getCategoryNameProperty() {
        return this.CategoryName;
    }
}
