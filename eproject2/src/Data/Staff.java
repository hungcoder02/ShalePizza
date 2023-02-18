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
public class Staff {

    private ObjectProperty<Integer> MemberId;
    private StringProperty Username, Password, FullName, Phone, Email;

    public Staff() {
        MemberId = new SimpleObjectProperty<>(null);
        Username = new SimpleStringProperty();
        Password = new SimpleStringProperty();
        FullName = new SimpleStringProperty();
        Phone = new SimpleStringProperty();
        Email = new SimpleStringProperty();
    }

    public Integer getMemberId() {
        return MemberId.get();
    }

    public String getUsername() {
        return Username.get();
    }

    public String getPassword() {
        return Password.get();
    }

    public String getFullName() {
        return FullName.get();
    }

    public String getPhone() {
        return Phone.get();
    }

    public String getEmail() {
        return Email.get();
    }

    public void setMemberId(Integer MemberId) {
        this.MemberId.set(MemberId);
    }

    public void setUsername(String Username) {
        this.Username.set(Username);
    }

    public void setPassword(String Password) {
        this.Password.set(Password);
    }

    public void setFullName(String FullName) {
        this.FullName.set(FullName);
    }

    public void setPhone(String Phone) {
        this.Phone.set(Phone);
    }

    public void setEmail(String Email) {
        this.Email.set(Email);
    }

    public ObjectProperty<Integer> getMemberIdProperty() {
        return this.MemberId;
    }

    public StringProperty getUsernameProperty() {
        return this.Username;
    }

    public StringProperty getPasswordProperty() {
        return this.Password;
    }

    public StringProperty getFullnameProperty() {
        return this.FullName;
    }

    public StringProperty getPhoneProperty() {
        return this.Phone;
    }

    public StringProperty getEmailProperty() {
        return this.Email;
    }
}
