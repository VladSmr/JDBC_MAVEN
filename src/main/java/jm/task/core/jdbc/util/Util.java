package jm.task.core.jdbc.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private String userName = "root";
    private String password = "godzun@1966";
    private String url = "jdbc:MySQL://localhost:3306/sys?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Database Connection Established...");
        } catch (Exception e) {
            System.out.println("Exception in connection");
            e.printStackTrace();
        }
        return conn;
    }
}