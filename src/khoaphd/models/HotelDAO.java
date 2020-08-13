package khoaphd.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import khoaphd.dtos.HotelDTO;
import khoaphd.utils.DBConnection;

/**
 *
 * @author KhoaPHD
 */
public class HotelDAO implements Serializable {
    
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
    
    public List<HotelDTO> getAllHotels()
            throws SQLException, ClassNotFoundException {
        List<HotelDTO> list = new ArrayList<>();
        String sql = "SELECT HotelID, HotelName, Area "
                + "FROM Hotel";
        try {
            con = DBConnection.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {                
                String hotelID = rs.getString("HotelID");
                String hotelName = rs.getString("HotelName");
                String area = rs.getString("Area");
                HotelDTO dto = new HotelDTO(hotelID, hotelName, area);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public String getHotelImagePath(String hotelID)
            throws SQLException, ClassNotFoundException {
        String result = "";
        String url = "SELECT Image "
                + "FROM Hotel "
                + "WHERE HotelID = ?";
        try {
            con = DBConnection.getConnection();
            stm = con.prepareStatement(url);
            stm.setString(1, hotelID);
            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getString("Image");
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public HotelDTO getHotelByID(String hotelID)
            throws SQLException, ClassNotFoundException {
        HotelDTO dto = null;
        String sql = "SELECT HotelName, Area "
                + "FROM Hotel "
                + "WHERE HotelID = ?";
        try {
            con = DBConnection.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, hotelID);
            rs = stm.executeQuery();
            if (rs.next()) {                
                String hotelName = rs.getString("HotelName");
                String area = rs.getString("Area");
                dto = new HotelDTO(hotelID, hotelName, area);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}