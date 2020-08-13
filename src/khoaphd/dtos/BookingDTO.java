package khoaphd.dtos;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author KhoaPHD
 */
public class BookingDTO implements Serializable {
    
    private String bookingID, userID, discountCode;
    private Date bookingDate;

    public BookingDTO(String userID, Date bookingDate) {
        this.userID = userID;
        this.bookingDate = bookingDate;
    }
    
    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
}