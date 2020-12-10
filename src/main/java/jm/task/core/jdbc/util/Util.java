package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {
    private final String userName = "root";
    private final String password = "godzun@1966";
    private final String url = "jdbc:MySQL://localhost:3306/sys?useSSL=true&verifyServerCertificate=false&serverTimezone=UTC";

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Database Connection Established...");
        } catch (Exception e) {
            throw new RuntimeException("Exception in connection", e);
        }
        return conn;
    }
}