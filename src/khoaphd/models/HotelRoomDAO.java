package khoaphd.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import khoaphd.dtos.HotelRoomDTO;
import khoaphd.utils.DBConnection;

/**
 *
 * @author KhoaPHD
 */
public class HotelRoomDAO implements Serializable {
    
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
    
    public List<HotelRoomDTO> getRoomInAllHotels()
            throws SQLException, ClassNotFoundException {
        List<HotelRoomDTO> list = new ArrayList<>();
        String sql = "SELECT HotelID, SUM(MaxQuantity) AS MaxQuantity "
                + "FROM HotelRoom "
                + "GROUP BY HotelID";
        try {
            con = DBConnection.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                String hotelID = rs.getString("HotelID");
                int maxQuantity = rs.getInt("MaxQuantity");
                HotelRoomDTO dto = new HotelRoomDTO(hotelID, maxQuantity);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public int getAllRoomsAmount(String hotelID)
            throws SQLException, ClassNotFoundException {
        int result = 0;
        String sql = "SELECT SUM(MaxQuantity) AS TotalAmount "
                + "FROM HotelRoom "
                + "WHERE HotelID = ? "
                + "GROUP BY HotelID";
        try {
            con = DBConnection.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, hotelID);
            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("TotalAmount");
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public List<HotelRoomDTO> getEachRoomTypeAmount(String hotelID)
            throws SQLException, ClassNotFoundException {
        List<HotelRoomDTO> list = new ArrayList<>();
        String sql = "SELECT RoomTypeID, Price, MaxQuantity "
                + "FROM HotelRoom "
                + "WHERE HotelID = ?";
        try {
            con = DBConnection.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, hotelID);
            rs = stm.executeQuery();
            while (rs.next()) {                
                String roomTypeID = rs.getString("RoomTypeID");
                double price = rs.getDouble("Price");
                int maxQuantity = rs.getInt("MaxQuantity");
                HotelRoomDTO dto = new HotelRoomDTO(roomTypeID, price, maxQuantity);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public int getRoomAmountByType(String hotelID, String roomTypeID)
            throws SQLException, ClassNotFoundException {
        int result = 0;
        String sql = "SELECT MaxQuantity "
                + "FROM HotelRoom "
                + "WHERE HotelID = ? AND RoomTypeID = ?";
        try {
            con = DBConnection.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, hotelID);
            stm.setString(2, roomTypeID);
            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("MaxQuantity");
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}