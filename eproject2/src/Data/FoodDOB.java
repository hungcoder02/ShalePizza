/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import javafx.collections.ObservableList;

/**
 *
 * @author Trang Tran
 */
public interface FoodDOB {

    public ObservableList<Food> selectAll();

    public ObservableList<Food> selectAll(int from, int to);

    public ObservableList<Food> selectAllCategoryName(String CategoryName, int from, int to);

    public int countFood();

    public int countFoodCategory(String CategoryName);

    public ObservableList<Food> selectAllCategoryName(String Categoryname);

    public Food insert(Food newFood);

    public boolean update(Food updateFood);

    public boolean delete(Food deleteFood);
}
