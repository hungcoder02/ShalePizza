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
public interface CategoryDOB {

    public ObservableList<Category> selectAll();

    public Category insert(Category newCategory);

    public boolean update(Category updateCategory);

    public boolean delete(Category deleteCategory);
}
