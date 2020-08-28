package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private String userName = "root";
    private String password = "root";
    private String url = "jdbc:MySQL://localhost:3306/User";

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.MySQL.jdbc.Driver");
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Database Connection Established...");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQLException");
            e.printStackTrace();
        }
        return conn;
    }
}