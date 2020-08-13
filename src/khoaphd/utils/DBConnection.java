package khoaphd.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author KhoaPHD
 */
public class DBConnection implements Serializable {
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=HotelManagement";
        Connection con = DriverManager.getConnection(url, "sa", "123456789");
        return con;
    }
}
