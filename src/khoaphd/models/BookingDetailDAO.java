package khoaphd.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import khoaphd.utils.DBConnection;

/**
 *
 * @author KhoaPHD
 */
public class BookingDetailDAO implements Serializable {
    
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;
    private final boolean ACTIVE = true;
    
    private void closeConnection() throws SQLException {
        if (rs != null)
            rs.close();
        if (stm != null)
            stm.close();
        if (con != null)
            con.close();
    }
    
    public Map<String, Integer> getBookedRooms(String hotelName, String area,
            Date checkinDate, Date checkoutDate)
            throws SQLException, ClassNotFoundException {
        Map<String, Integer> map = new HashMap<>();
        String sql = "SELECT BD.HotelID, SUM(BD.Quantity) AS BookedRoomAmount "
                + "FROM BookingDetail BD JOIN Hotel H ON BD.HotelID = H.HotelID "
                + "WHERE H.HotelName LIKE ? AND H.Area LIKE ? "
                + "AND CheckoutDate > ? AND CheckinDate < ? AND Status = ? "
                + "GROUP BY BD.HotelID";
        try {
            con = DBConnection.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + hotelName + "%");
            stm.setString(2, "%" + area + "%");
            stm.setDate(3, checkinDate);
            stm.setDate(4, checkoutDate);
            stm.setBoolean(5, ACTIVE);
            rs = stm.executeQuery();
            while (rs.next()) {                
                String hotelID = rs.getString("HotelID");
                int bookedRoomAmount = rs.getInt("BookedRoomAmount");
                map.put(hotelID, bookedRoomAmount);
            }
        } finally {
            closeConnection();
        }
        return map;
    }
    
    public Map<String, Integer> getBookedRoomTypes(String hotelID, 
            Date checkinDate, Date checkoutDate)
            throws SQLException, ClassNotFoundException {
        Map<String, Integer> map = new HashMap();
        String sql = "SELECT RoomTypeID, SUM(Quantity) AS BookedRoomAmount "
                + "FROM BookingDetail "
                + "WHERE HotelID = ? AND CheckoutDate > ? "
                + "AND CheckinDate < ? AND Status = ? "
                + "GROUP BY RoomTypeID";
        try {
            con = DBConnection.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, hotelID);
            stm.setDate(2, checkinDate);
            stm.setDate(3, checkoutDate);
            stm.setBoolean(4, ACTIVE);
            rs = stm.executeQuery();
            while (rs.next()) {
                String roomTypeID = rs.getString("RoomTypeID");
                Integer bookedRoomAmount = rs.getInt("BookedRoomAmount");
                map.put(roomTypeID, bookedRoomAmount);
            }
        } finally {
            closeConnection();
        }
        return map;
    }
}