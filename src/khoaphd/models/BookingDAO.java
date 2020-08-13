/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoaphd.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import khoaphd.dtos.BookingDTO;
import khoaphd.utils.DBConnection;

/**
 *
 * @author KhoaPHD
 */
public class BookingDAO implements Serializable {
    
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;
    
    private void closeConnection() throws SQLException {
        if (rs != null)
            rs.close();
        if (stm != null)
            stm.close();
        if (con != null)
            con.close();
    }
    
    public boolean insertNewBooking(BookingDTO dto)
            throws SQLException, ClassNotFoundException {
        boolean result = false;
        String sql = "INSERT INTO Booking(UserID, BookingDate) "
                + "VALUES(?,?)";
        try {
            con = DBConnection.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getUserID());
            stm.setDate(2, dto.getBookingDate());
            result = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
}
