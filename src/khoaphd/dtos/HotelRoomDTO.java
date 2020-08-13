package khoaphd.dtos;

import java.io.Serializable;

/**
 *
 * @author KhoaPHD
 */
public class HotelRoomDTO implements Serializable {
    
    private String hotelID, roomTypeID;
    private double price;
    private int maxQuantity;

    public HotelRoomDTO(String hotelID, String roomTypeID, double price, int maxQuantity) {
        this.hotelID = hotelID;
        this.roomTypeID = roomTypeID;
        this.price = price;
        this.maxQuantity = maxQuantity;
    }
    
    public HotelRoomDTO(String roomTypeID, double price, int maxQuantity) {
        this.roomTypeID = roomTypeID;
        this.price = price;
        this.maxQuantity = maxQuantity;
    }

    public HotelRoomDTO(String hotelID, int maxQuantity) {
        this.hotelID = hotelID;
        this.maxQuantity = maxQuantity;
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

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }
}