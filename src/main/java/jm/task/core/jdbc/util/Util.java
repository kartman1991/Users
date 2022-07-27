package jm.task.core.jdbc.util;
import java.sql.*;

public class Util {
    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "rootroot");
            } catch (SQLException e) {
            System.out.println("Not connected");
        }
        return connection;
    }
}
