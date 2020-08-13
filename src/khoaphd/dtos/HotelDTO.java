package khoaphd.dtos;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author KhoaPHD
 */
public class HotelDTO implements Serializable {
    
    private String hotelID, hotelName, area, imagePath;

    public HotelDTO(String hotelID, String hotelName, String area) {
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.area = area;
    }
    
    public Vector toVector() {
        Vector v = new Vector();
        v.add(hotelID);
        v.add(hotelName);
        v.add(area);
        return v;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}