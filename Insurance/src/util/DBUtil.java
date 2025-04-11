package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/insurance_db";
        String username = "root";
        String password = "pooji@2004";
        return DriverManager.getConnection(url, username, password);
    }
}
