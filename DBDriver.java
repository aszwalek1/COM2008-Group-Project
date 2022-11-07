import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBDriver {
    static String URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/";
    static String DBNAME = "team024";
    static String USER = "team024";
    static String PASSWORD = "5f5f3eba";

    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(URL+DBNAME, USER, PASSWORD);
            System.out.println("Connection");
            return con;
        }
        catch(SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void closeConnection(Connection con) {
        try {
            con.close();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

}

/* Now can just use the code below:

    Connection con = DBDriver.getConnection();
    DBDriver.closeConnection(con);

*/