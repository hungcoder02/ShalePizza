/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import DBConnect.DbFactory;
import DBConnect.DbType;
import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Trang Tran
 */
public class StaffDAOimpl implements StaffDOB {

    public DbType database = DbType.MYSQL;

    @Override
    public ObservableList<Staff> selectAll() {
        ObservableList<Staff> Staffs = FXCollections.observableArrayList();
        try (Connection conn = DbFactory.getConnection(database);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Clerk");) {
            while (rs.next()) {
                Staff a = new Staff();
                a.setMemberId(rs.getInt("MemberID"));
                a.setUsername(rs.getString("UserName"));
                a.setEmail(rs.getString("Email"));
                a.setFullName(rs.getString("FullName"));
                a.setPhone(rs.getString("Phone"));
                a.setPassword(rs.getString("Password"));
                Staffs.add(a);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return Staffs;
    }

    @Override
    public Staff insert(Staff newStaff) {
        String sql = "INSERT into Clerk (UserName,Password,FullName,Phone,Email) ";
        sql += "VALUES (?, ?, ?, ?, ?)";
        ResultSet key = null;
        try (
                Connection conn = DbFactory.getConnection(database);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ) {
            stmt.setString(1, newStaff.getUsername());
            stmt.setString(2, newStaff.getPassword());
            stmt.setString(3, newStaff.getFullName());
            stmt.setString(4, newStaff.getPhone());
            stmt.setString(5, newStaff.getEmail());
            
            int rowInserted = stmt.executeUpdate();
            if (rowInserted == 1) {
                key = stmt.getGeneratedKeys();
                key.next();
                String newKey = key.getString(1);
                newStaff.setUsername(newKey);
                return newStaff;
            } else {
                System.err.println("No Member inserted");
                return null;
            }
        } catch (Exception e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                if (key != null) {
                    key.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    @Override
    public boolean update(Staff updateStaff) {
        String sql = "UPDATE Clerk SET "
                + "UserName = ?, "
                + "Password = ?, "
                + "FullName = ?, "
                + "Email = ?, "
                + "Phone = ? "
                + "WHERE MemberID = ?";
        try (
                Connection conn = DbFactory.getConnection(database);
                PreparedStatement stmt = conn.prepareStatement(sql);
            ) {
            
            stmt.setString(1, updateStaff.getUsername());
            stmt.setString(2, updateStaff.getPassword());
            stmt.setString(3, updateStaff.getFullName());
            stmt.setString(4, updateStaff.getEmail());
            stmt.setString(5, updateStaff.getPhone());
            stmt.setInt(6, updateStaff.getMemberId());      
     
            int rowUpdated = stmt.executeUpdate();

            if (rowUpdated == 1) {
                return true;
            } else {
                System.err.println("No Member updated");
                return false;
            }
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    @Override
    public boolean delete(Staff deleteStaff) {
        String sql = "DELETE FROM Clerk WHERE Username = ?";
        try (
                Connection conn = DbFactory.getConnection(database);
                PreparedStatement stmt = conn.prepareStatement(sql);
                ) {
            
            stmt.setString(1, deleteStaff.getUsername());
            int rowDeleted = stmt.executeUpdate();

            if (rowDeleted == 1) {
                return true;
            } else {
                System.err.println("No member deleted");
                return false;
            }

        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

}
