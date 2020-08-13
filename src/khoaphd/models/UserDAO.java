package khoaphd.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import khoaphd.dtos.UserDTO;
import khoaphd.utils.DBConnection;

/**
 *
 * @author KhoaPHD
 */
public class UserDAO implements Serializable {
    
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
    
    public UserDTO checkLogin(String userID, String password)
            throws SQLException, ClassNotFoundException {
        UserDTO dto = null;
        String sql = "SELECT UserName, Role "
                + "FROM [User] "
                + "WHERE UserID = ? AND Password = ? AND Status = ?";
        try {
            con = DBConnection.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, userID);
            stm.setString(2, password);
            stm.setBoolean(3, ACTIVE);
            rs = stm.executeQuery();
            if (rs.next()) {
                String userName = rs.getString("UserName");
                String role = rs.getString("Role");
                dto = new UserDTO(userID, userName, role);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public boolean insertNewUser(UserDTO dto)
            throws SQLException, ClassNotFoundException {
        boolean result = false;
        String sql = "INSERT INTO [User](UserID, Password, UserName, Phone, Address, Role, Status, CreateDate) "
                + "VALUES(?,?,?,?,?,?,?,?)";
        try {
            con = DBConnection.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getUserID());
            stm.setString(2, dto.getPassword());
            stm.setString(3, dto.getUserName());
            stm.setString(4, dto.getPhone());
            stm.setString(5, dto.getAddress());
            stm.setString(6, dto.getRole());
            stm.setBoolean(7, ACTIVE);
            stm.setDate(8, new Date(System.currentTimeMillis()));
            result = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
}