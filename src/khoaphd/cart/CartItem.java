package khoaphd.cart;

import java.io.Serializable;
import java.sql.Date;
import java.util.Vector;

/**
 *
 * @author KhoaPHD
 */
public class CartItem implements Serializable {
    
    private String hotelID, roomTypeID;
    double price;
    int quantity;
    Date checkin, checkout;

    public CartItem(String hotelID, String roomTypeID, double price, int quantity, Date checkin, Date checkout) {
        this.hotelID = hotelID;
        this.roomTypeID = roomTypeID;
        this.price = price;
        this.quantity = quantity;
        this.checkin = checkin;
        this.checkout = checkout;
    }
    
    public Vector toVector() {
        Vector v = new Vector();
        v.add(hotelID);
        v.add(roomTypeID);
        v.add(price);
        v.add(quantity);
        v.add(checkin);
        v.add(checkout);
        return v;
    }
    
    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public String getRoomTypeID() {
        return roomTypeID;
    }

    public void setRoomTypeID(String roomTypeID) {
        this.roomTypeID = roomTypeID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }
}