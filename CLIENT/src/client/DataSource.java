package client;



import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataSource {
    
    private static String url = "jdbc:mysql://localhost:3306/chat";
    private static String username = "root";
    private static String password = "";
    private Connection con;
   private static DataSource data;
    private DataSource() {
        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("connexion établie");
        } catch (SQLException e) {
        }
    }
    public Connection getConnection()
    {return con;
    }

    public static DataSource getInstance() {
        if (data == null) {
             data = new DataSource();
        }

        return data;
    }
}
