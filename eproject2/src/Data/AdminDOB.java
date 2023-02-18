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
public interface AdminDOB {

    public ObservableList<Admin> selectAll();

    public boolean selectLogin(String Username);

}
