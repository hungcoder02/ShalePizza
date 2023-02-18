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
public interface StaffDOB {

    public ObservableList<Staff> selectAll();

    public Staff insert(Staff newAdmin);

    public boolean update(Staff updateAdmin);

    public boolean delete(Staff deleteAdmin);
}
