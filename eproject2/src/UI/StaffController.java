/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DBConnect.DbFactory;
import DBConnect.DbType;
import Data.Staff;
import Data.StaffDAOimpl;
import Data.StaffDOB;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author Trang Tran
 */
public class StaffController {

    String strimg;
    public DbType database = DbType.MYSQL;
    private Staff staffEdit = null;
    private StaffDOB staffDAO = new StaffDAOimpl();

    @FXML
    private TextField txtSearch;

    @FXML
    private Label lbUpdate;

    @FXML
    private JFXTextField txtUser;

    @FXML
    private Label ErrUser;

    @FXML
    private JFXTextField txtName;

    @FXML
    private Label ErrName;

    @FXML
    private JFXTextField txtPhone;

    @FXML
    private Label ErrPhone;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private Label ErrEmail;

    @FXML
    private JFXPasswordField txtPass;

    @FXML
    private Label ErrPass;

    @FXML
    private TableView<Staff> tvStaff;

    @FXML
    private TableColumn<Staff, String> tcUser;

    @FXML
    private TableColumn<Staff, String> tcName;

    @FXML
    private TableColumn<Staff, String> tcPhone;

    @FXML
    private TableColumn<Staff, String> tcEmail;

    @FXML
    private TableColumn<Staff, String> tcPass;

    @FXML
    void ClickCart(ActionEvent event) throws IOException {
        Navigator.getInstance().goToOrderDetails();
    }

    @FXML
    void ClickHome(ActionEvent event) throws IOException {
        Navigator.getInstance().goToHome();
    }

    @FXML
    void ClickLogout(ActionEvent event) throws IOException {
        Navigator.getInstance().goToLogin(null);
    }

    @FXML
    void ClickReset(ActionEvent event) throws IOException {
        Navigator.getInstance().goToStaff();
    }

    @FXML
    void ClickStaff(ActionEvent event) throws IOException {
        Navigator.getInstance().goToStaff();
    }

    @FXML
    void ClickCategory(ActionEvent event) throws IOException {
        Navigator.getInstance().goToCategory();
    }

    @FXML
    void ClickFood(ActionEvent event) throws IOException {
        Navigator.getInstance().goToFood();
    }

    @FXML
    void ClickSearch(ActionEvent event) {
        String sql = "SELECT * FROM Clerk "
                + "WHERE Username LIKE '%" + txtSearch.getText() + "%' ";

        ObservableList<Staff> Staffs = FXCollections.observableArrayList();
        try (Connection conn = DbFactory.getConnection(database);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);) {
            while (rs.next()) {
                Staff a = new Staff();
                a.setUsername(rs.getString("Username"));
                a.setPassword(rs.getString("Password"));
                a.setFullName(rs.getString("FullName"));
                a.setPhone(rs.getString("Phone"));
                a.setEmail(rs.getString("Email"));
                a.setMemberId(rs.getInt("MemberId"));
                Staffs.add(a);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        tvStaff.setItems(Staffs);
        tcUser.setCellValueFactory((admin) -> {
            return admin.getValue().getUsernameProperty();
        });
        tcName.setCellValueFactory((admin) -> {
            return admin.getValue().getFullnameProperty();
        });
        tcPhone.setCellValueFactory((admin) -> {
            return admin.getValue().getPhoneProperty();
        });
        tcEmail.setCellValueFactory((admin) -> {
            return admin.getValue().getEmailProperty();
        });
        tcPass.setCellValueFactory((admin) -> {
            return admin.getValue().getPasswordProperty();
        });
    }

    @FXML
    void ClickDelete(ActionEvent event) {
        Staff selectedStaff = tvStaff.getSelectionModel().getSelectedItem();

        if (selectedStaff == null) {
            Warning();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Staff");
            alert.setHeaderText("Do you agree to delete Staff?");
            Optional<ButtonType> confirmationResponse
                    = alert.showAndWait();
            if (confirmationResponse.get() == ButtonType.OK) {
                boolean result = staffDAO.delete(selectedStaff);

                if (result) {
                    tvStaff.getItems().remove(selectedStaff);
                    System.out.println("A Staff is deleted");
                } else {
                    System.err.println("No Staff is deleted");
                }
            }
        }
    }

    @FXML
    void ClickSubmit(ActionEvent event) {
//        resetTxt();
        if (lbUpdate.getText().equals("Update")) {
            Staff updateStaff = extractFromFields();
            updateStaff.setMemberId(this.staffEdit.getMemberId());
            boolean result = staffDAO.update(updateStaff);
            System.out.println(updateStaff.getMemberId());
        } else {
            Staff inserStaff = extractFromFields();
            inserStaff = staffDAO.insert(inserStaff);
        }

    }

    @FXML
    void ClickUpdate(ActionEvent event) {
        lbUpdate.setText("Update");

        Staff updateStaff = tvStaff.getSelectionModel().getSelectedItem();
        txtEmail.setText(updateStaff.getEmail());
        txtName.setText(updateStaff.getFullName());
        txtPass.setText(updateStaff.getPassword());
        txtPhone.setText(updateStaff.getPhone());
        txtUser.setText(updateStaff.getUsername());

        staffEdit = updateStaff;
    }

    public void initialize() {
        tvStaff.setItems(staffDAO.selectAll());
        tcUser.setCellValueFactory((admin) -> {
            return admin.getValue().getUsernameProperty();
        });
        tcEmail.setCellValueFactory((admin) -> {
            return admin.getValue().getEmailProperty();
        });
        tcPhone.setCellValueFactory((admin) -> {
            return admin.getValue().getPhoneProperty();
        });
        tcName.setCellValueFactory((admin) -> {
            return admin.getValue().getFullnameProperty();
        });
        tcPass.setCellValueFactory((admin) -> {
            return admin.getValue().getPasswordProperty();
        });

        txtUser.textProperty().addListener((observable, oldValue, newValue) -> {
            ErrUser.setText("");
            if (isNull(txtUser.getText())) {
                ErrUser.setText("Username not be empty!");
            }
        });
        txtName.textProperty().addListener((observable, oldValue, newValue) -> {
            ErrName.setText("");
            if (isNull(txtName.getText())) {
                ErrName.setText("Full Name not be empty!");
            }
        });
        txtPhone.textProperty().addListener((observable, oldValue, newValue) -> {
            ErrPhone.setText("");
            if (isNull(txtPhone.getText())) {
                ErrPhone.setText("Phone not be empty!");
            }
        });
        txtEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            ErrEmail.setText("");
            if (isNull(txtEmail.getText())) {
                ErrEmail.setText("Email not be empty!");
            }
        });
        txtPass.textProperty().addListener((observable, oldValue, newValue) -> {
            ErrPass.setText("");
            if (isNull(txtPass.getText())) {
                ErrPass.setText("Password not be empty!");
            }
        });
    }

    private Staff extractFromFields() {
        Staff saff = new Staff();
        saff.setUsername(txtUser.getText());
        saff.setPassword(sha1(txtPass.getText()));
        saff.setFullName(txtName.getText());
        saff.setPhone(txtPhone.getText());
        saff.setEmail(txtEmail.getText());

        return saff;
    }

    private String sha1(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private void resetTxt() {
        txtEmail.setText("");
        txtName.setText("");
        txtPass.setText("");
        txtPhone.setText("");
        txtUser.setText("");
    }

    private void Warning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Member Warning");
        alert.setHeaderText("You must choose to perform");
        alert.showAndWait();
    }

    public boolean isNull(String str) {
        if (str.equals("")) {
            return true;
        } else {
            return false;
        }
    }
}
