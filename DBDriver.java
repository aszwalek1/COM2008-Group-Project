import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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

    //---------------------------------------------------
    //REGISTER PAGE FUNCTIONS
    //---------------------------------------------------
    public static boolean addressExists(int houseNo, String postcode)  throws SQLException
    {
        Connection con = DBDriver.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Address WHERE houseNo = '" + houseNo + "' AND postcode = '"+ postcode +"'");
        boolean exists = rs.next();
        con.close();
        return exists;

    }

    public static void insertCustomerRecord(String forename, String surname, int houseNo, String streetName, String cityName, String postcode) throws SQLException
    {
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        //Check if the Address exists
        if(!addressExists(houseNo, postcode))
        {
            //Add Address
            addressInsertRecord(houseNo, streetName, cityName, postcode);
        }
        //Add Customer
        String sql = "INSERT INTO Customer (forename,surname,houseNo,postcode) VALUES ('" + forename + "','" + surname + "'," + houseNo + ",'" + postcode + "')";
        System.out.println("Inserting record into the Customer table...");
        try
        {
            stmt.executeUpdate(sql);
            System.out.println("Customer insertion successful");
        }
        catch (SQLException ex)
        {
            System.out.println("Customer insertion unsuccessful");
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
    //-----------------------------------------------------
    //END OF REGISTER PAGE FUNCTIONS
    //-----------------------------------------------------

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

    public static Boolean staffCheckPass(String passIn, String passDB) throws NoSuchAlgorithmException {
        String passInEncrypted;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        passInEncrypted = new String(digest.digest(passIn.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        if (passInEncrypted.equals(passDB)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    //---------------------------------------------------------------------
    //BROWSE PAGE FUNCTIONS
    //---------------------------------------------------------------------

    public static ArrayList<String> allFramesInStock(){
        try{
            Connection con = DBDriver.getConnection();
            Statement stmt = con.createStatement();
            ArrayList<String> frameList = new ArrayList<String>();
            ResultSet rs = stmt.executeQuery("SELECT frameId, brandName, productName, gears, shocks, size, unitCost FROM FrameSet INNER JOIN Product ON frameId = productId WHERE stock > 0;");
            while (rs.next()) {
                frameList.add(rs.getInt("frameId")+". "+rs.getString("brandName")+" '"+rs.getString("productName")+
                        "' - Gears: "+rs.getInt("gears")+", Shocks: "+boolIntToString(rs.getInt("shocks"))+", Size: "+
                        rs.getDouble("size")+", Cost: £"+rs.getDouble("unitCost"));
            }
            closeConnection(con);
            return frameList;
        }
        catch(SQLException ex)
        {
            System.out.println("Connection to Database unsuccessful");
            ex.printStackTrace();
            ArrayList<String> emptyList = new ArrayList<String>();
            return emptyList;
        }


    }

    public static ArrayList<String> allWheelsInStock(){
        try{
            Connection con = DBDriver.getConnection();
            Statement stmt = con.createStatement();
            ArrayList<String> wheelList = new ArrayList<String>();
            ResultSet rs = stmt.executeQuery("SELECT wheelId, brandName, productName, style, brakes, diameter, unitCost FROM Wheel INNER JOIN Product ON wheelId = productId WHERE stock > 1;");
            while (rs.next()) {
                wheelList.add(rs.getInt("wheelId")+". "+rs.getString("brandName")+" '"+rs.getString("productName")+
                        "' - Style: "+rs.getString("style")+", Brakes: "+rs.getString("brakes")+", Diameter: "+
                        rs.getDouble("diameter")+", Cost: £"+rs.getDouble("unitCost"));
            }
            closeConnection(con);
            return wheelList;
        }
        catch(SQLException ex){
            System.out.println("Connection to Database unsuccessful");
            ex.printStackTrace();
            ArrayList<String> emptyList = new ArrayList<String>();
            return emptyList;
        }


    }

    public static ArrayList<String> allHandlebarsInStock(){
        try{
            Connection con = DBDriver.getConnection();
            Statement stmt = con.createStatement();
            ArrayList<String> handlebarList = new ArrayList<String>();
            ResultSet rs = stmt.executeQuery("SELECT handlebarId, brandName, productName, handlebarStyle, unitCost FROM Handlebar INNER JOIN Product ON handlebarId = productId WHERE stock > 0;");
            while (rs.next()) {
                handlebarList.add(rs.getInt("handlebarId")+". "+rs.getString("brandName")+" '"+rs.getString("productName")+
                        "' - Style: "+rs.getString("handlebarStyle")+", Cost: £"+rs.getDouble("unitCost"));
            }
            closeConnection(con);
            return handlebarList;
        }
        catch (SQLException ex){
            System.out.println("Connection to Database unsuccessful");
            ex.printStackTrace();
            ArrayList<String> emptyList = new ArrayList<String>();
            return emptyList;
        }


    }

    public static ArrayList<String> allAssembledBikesInStock(){
        try{
            Connection con = DBDriver.getConnection();
            Statement stmt = con.createStatement();
            ArrayList<String> bikeList = new ArrayList<String>();
            ResultSet rs = stmt.executeQuery("SELECT assembledBikeId, aPro.brandName, aPro.productName, " +
                    "gears AS frameGears, shocks AS frameShocks, size AS frameSize, handlebarStyle, " +
                    "diameter AS wheelDiameter, brakes AS wheelBrakes, style AS wheelStyle, " +
                    "(aPro.unitCost+fPro.unitCost+hPro.unitCost+wPro.unitCost+wPro.unitCost) AS total FROM AssembledBike " +
                    "INNER JOIN Product AS aPro ON assembledBikeId = aPro.productId " +
                    "INNER JOIN FrameSet ON AssembledBike.frameId = FrameSet.frameId " +
                    "INNER JOIN Handlebar ON AssembledBike.handlebarId = Handlebar.handlebarId " +
                    "INNER JOIN Wheel ON AssembledBike.wheelId = Wheel.wheelId " +
                    "INNER JOIN Product AS fPro ON FrameSet.frameId = fPro.productId " +
                    "INNER JOIN Product AS hPro ON Handlebar.handlebarId = hPro.productId " +
                    "INNER JOIN Product AS wPro ON Wheel.wheelId = wPro.productId WHERE aPro.stock > 0;");
            while (rs.next()) {
                bikeList.add(rs.getInt("assembledBikeId")+". "+rs.getString("brandName")+" '"+rs.getString("productName")+
                        "' - Frame Gears: "+rs.getInt("frameGears")+", Frame Shocks: "+boolIntToString(rs.getInt("frameShocks"))+
                        ", Frame Size: "+rs.getDouble("frameSize")+", Handlebar Style: "+rs.getString("handlebarStyle")+
                        ", Wheel Diameter: "+rs.getDouble("wheelDiameter")+", Wheel Brakes: "+rs.getString("wheelBrakes")+
                        ", Wheel Style: "+rs.getString("wheelStyle")+", Total Cost: £"+rs.getDouble("total"));
            }
            closeConnection(con);
            return bikeList;
        }
        catch (SQLException ex){
            System.out.println("Connection to Database unsuccessful");
            ex.printStackTrace();
            ArrayList<String> emptyList = new ArrayList<String>();
            return emptyList;
        }


    }

    public static String boolIntToString(int tinyInt)
    {
        if(tinyInt == 1){
            return "Yes";
        }
        else{
            return "No";
        }
    }

    //---------------------------------------------------------------------
    //END OF BROWSE PAGE FUNCTIONS
    //---------------------------------------------------------------------

    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException {

        //customerSelectAll();

        //staffSelectAll();

        //customerInsertRecord("Waigen", "Waigen", 18, "S10 5DF");

        //addressInsertRecord(14, "Manchester Road", "Sheffield", "S10 5DF");

        //addressSelectAll();

        staffLogin("Staff1","hddsa");

    }
}