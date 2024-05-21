package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String USERNAME="root";
    private static final String PASSWORD="";
    private static final String URL="jdbc:mysql://localhost:3306/efm_java_jee";
    private static final String DRIVER_NAME="com.mysql.cj.jdbc.Driver";

    public static Connection getConnection(){
        Connection cnx;
        try {
            Class.forName(DRIVER_NAME);
            cnx = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return cnx;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
