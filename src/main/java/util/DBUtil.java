package util;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBUtil {

    
    
    public static Connection getConnection(String dbPath) throws Exception {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection("jdbc:sqlite:"+ dbPath);
        
    }
}