package khoaphd.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import khoaphd.utils.DBConnection;

/**
 *
 * @author KhoaPHD
 */
public class RoomTypeDAO implements Serializable {
    
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
    
    public String getRoomType(String roomTypeID)
            throws SQLException, ClassNotFoundException {
        String result = "Unknown";
        String sql = "SELECT RoomType "
                + "FROM RoomType "
                + "WHERE RoomTypeID = ?";
        try {
            con = DBConnection.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, roomTypeID);
            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getString("RoomType");
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}