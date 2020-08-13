package khoaphd.dtos;

import java.io.Serializable;
import khoaphd.cart.CartObject;

/**
 *
 * @author KhoaPHD
 */
public class UserDTO implements Serializable {
    
    private String userID, password, userName, phone, address, role;
    private CartObject cart;

    public UserDTO(String userID, String userName, String role) {
        this.userID = userID;
        this.userName = userName;
        this.role = role;
    }

    public UserDTO(String userID, String password, String userName, String phone, String address, String role) {
        this.userID = userID;
        this.password = password;
        this.userName = userName;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }
    
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public CartObject getCart() {
        return cart;
    }

    public void setCart(CartObject cart) {
        this.cart = cart;
    }
}