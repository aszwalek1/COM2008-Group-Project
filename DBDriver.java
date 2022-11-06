import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBDriver {
    static String URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/";
    static String DBNAME = "team024";
    static String USER = "team024";
    static String PASSWORD = "5f5f3eba";

    public static void main(String[] args) {
        System.out.println("Connecting...");

        try {
            Connection conn = DriverManager.getConnection(URL+DBNAME, USER, PASSWORD);
            System.out.println("Connection");
            conn.close();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

}

