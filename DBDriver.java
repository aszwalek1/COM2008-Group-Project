import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;


public class DBDriver {
    static String URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/";
    static String DBNAME = "team024";
    static String USER = "team024";
    static String PASSWORD = "5f5f3eba";

    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(URL + DBNAME, USER, PASSWORD);
            System.out.println("Connection to Database successful");
            return con;
        } catch (SQLException ex) {
            System.out.println("Connection to Database unsuccessful");
            ex.printStackTrace();
            return null;
        }
    }

    public static void closeConnection(Connection con) {
        try {
            con.close();
            System.out.println("Connection to Database closed successfully");
        } catch (SQLException ex) {
            System.out.println("Connection to Database could not be closed successfully");
            ex.printStackTrace();
        }
    }

    public static void customerSelectAll() throws SQLException {
        Connection con = DBDriver.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Customer");
        while (rs.next()) {
            System.out.print("Customer ID: " + rs.getInt("customerId"));
            System.out.print(", Forename: " + rs.getString("forename"));
            System.out.print(", Surname: " + rs.getString("surname"));
            System.out.print(", HouseNo: " + rs.getInt("houseNo"));
            System.out.println(", Postcode: " + rs.getString("postcode"));
        }
        closeConnection(con);
    }

    public static void staffSelectAll() throws SQLException {
        Connection con = DBDriver.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Staff");
        while (rs.next()) {
            System.out.print("Staff Username: " + rs.getString("staffUsername"));
            System.out.println(", Staff Password: " + rs.getString("sPassword"));
        }
        closeConnection(con);
    }

    public static void addressSelectAll() throws SQLException {
        Connection con = DBDriver.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Address");
        while (rs.next()) {
            System.out.print("HouseNo: " + rs.getString("houseNo"));
            System.out.print(", Street Name: " + rs.getString("streetName"));
            System.out.print(", City Name: " + rs.getString("cityName"));
            System.out.println(", Postcode: " + rs.getString("postcode"));
        }
        closeConnection(con);
    }

    public static void customerInsertRecord(String forename, String surname, int houseNo, String postcode) throws SQLException {
        Connection con = DBDriver.getConnection();
        Statement stmt = con.createStatement();
        System.out.println("Inserting record into the Customer table...");
        String intoTable = "INSERT INTO Customer (forename, surname, houseNo, postcode) VALUES (";
        String sql = intoTable + forename + "," + surname + "," + houseNo + ",\"" + postcode + "\")";
        try {
            stmt.executeUpdate(sql);
            System.out.println("Record insertion successful");
        } catch (SQLException ex) {
            System.out.println("Record insertion unsuccessful");
            ex.printStackTrace();
        }
        closeConnection(con);
    }

    public static void addressInsertRecord(int houseNo, String streetName, String cityName, String postcode) throws SQLException {
        Connection con = DBDriver.getConnection();
        Statement stmt = con.createStatement();
        System.out.println("Inserting record into the Address table...");
        String intoTable = "INSERT INTO Address (houseNo, streetName, cityName, postcode) VALUES (";
        String sql = intoTable + houseNo + ",\"" + streetName + "\",\"" + cityName + "\",\"" + postcode + "\")";
        try {
            stmt.executeUpdate(sql);
            System.out.println("Record insertion successful");
        } catch (SQLException ex) {
            System.out.println("Record insertion unsuccessful");
            ex.printStackTrace();
        }
        closeConnection(con);
    }

    //////////////// staff stuff
    public static void staffLogin(String userIn, String passIn) throws SQLException, NoSuchAlgorithmException {
        String userDB; String passDB = "";
        Connection con = DBDriver.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Staff WHERE staffUsername = '" + userIn + "'");
        while (rs.next()) {
            userDB = rs.getString("staffUsername");
            System.out.println("Username: " + userDB);
            passDB = rs.getString("sPassword");
            System.out.println("Password: " + passDB);
        }
        staffCheckPass(passIn, passDB);
        closeConnection(con);
    }

    public static Boolean staffCheckPass(String passIn, String passDB) throws SQLException, NoSuchAlgorithmException {
        String passInEncrypted;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        passInEncrypted = new String(digest.digest(passIn.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        if (passInEncrypted.equals(passDB)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException {

        //customerSelectAll();

        //staffSelectAll();

        //customerInsertRecord("Waigen", "Waigen", 18, "S10 5DF");

        //addressInsertRecord(14, "Manchester Road", "Sheffield", "S10 5DF");

        //addressSelectAll();

        staffLogin("Staff1","hddsa");

    }
}