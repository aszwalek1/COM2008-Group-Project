import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;


public class DBDriver {
    static String URL = "jdbc:mysql://stusql.dcs.shef.ac.uk/";
    static String DBNAME = "team024";
    static String USER = "team024";
    static String PASSWORD = "5f5f3eba";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL + DBNAME, USER, PASSWORD);
        } catch (SQLException ex) {
            System.out.println("Connection to Database unsuccessful");
            ex.printStackTrace();
            return null;
        }
    }

    public static void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("Connection to Database could not be closed successfully");
            ex.printStackTrace();
        }
    }

    //---------------------------------------------------
    //REGISTER PAGE FUNCTIONS
    //---------------------------------------------------

    public static boolean addressExists(String houseNo, String postcode) {
        try {
            Connection con = DBDriver.getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Address WHERE houseNo = '" + houseNo + "' AND postcode = '"+ postcode +"'");
            boolean exists = rs.next();
            con.close();
            return exists;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean customerExists(int customerId) {
        try {
            Connection con = DBDriver.getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Customer WHERE customerId = " + customerId);
            boolean exists = rs.next();
            con.close();
            return exists;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static void insertCustomerRecord(String forename, String surname, String houseNo, String streetName, String cityName, String postcode) throws SQLException {
        Connection con = getConnection();
        Statement stmt = Objects.requireNonNull(con).createStatement();
        if(!addressExists(houseNo, postcode)) {    //Check if the Address exists
            addressInsertRecord(houseNo, streetName, cityName, postcode);   //Add Address
        }
        String sql = "INSERT INTO Customer (forename,surname,houseNo,postcode) VALUES ('" + forename + "','" + surname + "'," + houseNo + ",'" + postcode + "')";   //Add Customer
        System.out.println("Inserting record into the Customer table...");
        try {
            stmt.executeUpdate(sql);
            System.out.println("Customer insertion successful");
        } catch (SQLException ex) {
            System.out.println("Customer insertion unsuccessful");
            ex.printStackTrace();
        }
        closeConnection(con);
    }

    public static void addressInsertRecord(String houseNo, String streetName, String cityName, String postcode) throws SQLException {
        Connection con = DBDriver.getConnection();
        Statement stmt = Objects.requireNonNull(con).createStatement();
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



    //-----------------------------------------------------
    //STAFF PAGE FUNCTIONS
    //-----------------------------------------------------

    public static boolean staffLogin(String userIn, String passIn) throws SQLException {
        String passDB = "";
        String passEncrypt = "";
        Connection con = DBDriver.getConnection();
        Statement stmt = Objects.requireNonNull(con).createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Staff WHERE staffUsername = '" + userIn + "'");
        while (rs.next()) {
            passDB = rs.getString("sPassword");
        }
        rs = stmt.executeQuery("SELECT SHA2('" + passIn + "', 256)");
        while (rs.next()) {
            passEncrypt = rs.getString(1);
        }
        closeConnection(con);
        return passEncrypt.equals(passDB);
    }

    public static ArrayList<String> allOrders(){
        try {
            Connection con = DBDriver.getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            ArrayList<String> orderList = new ArrayList<>();
            ResultSet rs = stmt.executeQuery("SELECT orderNo, staffUsername, customerId, " +
                "CONCAT(fPro.brandName,' - ', fPro.productName) AS frameName, " +
                "CONCAT(hPro.brandName,' - ', hPro.productName) AS handlebarName, " +
                "CONCAT(wPro.brandName,' - ', wPro.productName) AS wheelName, " +
                "orderDate, orderCost, orderStatus FROM Orders " +
                "INNER JOIN AssembledBike ON AssembledBike.assembledBikeId = Orders.assembledBikeId " +
                "INNER JOIN FrameSet ON AssembledBike.frameId = FrameSet.frameId " +
                "INNER JOIN Handlebar ON AssembledBike.handlebarId = Handlebar.handlebarId " +
                "INNER JOIN Wheel ON AssembledBike.wheelId = Wheel.wheelId " +
                "INNER JOIN Product AS fPro ON FrameSet.frameId = fPro.productId " +
                "INNER JOIN Product AS hPro ON Handlebar.handlebarId = hPro.productId " +
                "INNER JOIN Product AS wPro ON Wheel.wheelId = wPro.productId;");
            while (rs.next()) {
                orderList.add(
                    rs.getInt("orderNo")+ ","+rs.getString("staffUsername")+","+
                        rs.getInt("customerId")+","+rs.getString("frameName")+","+
                        rs.getString("handlebarName")+","+rs.getString("wheelName")+","+
                        rs.getDate("orderDate")+",£"+rs.getDouble("orderCost")+","+
                        rs.getString("orderStatus"));
            }
            closeConnection(con);
            return orderList;
        } catch (SQLException ex) {
            System.out.println("Connection to Database unsuccessful");
            ex.printStackTrace();
            return new ArrayList<>(); //returns empty list if cant connect
        }
    }

    public static void UpdateOrderStatus(int orderNo, String status) {
        try {
            Connection con = getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            stmt.executeUpdate("UPDATE Orders SET orderStatus = '" + status + "' WHERE orderNo = "+ orderNo);
            closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void UpdateOrderStatus(int orderNo, String status, String username) {
        try {
            Connection con = getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            stmt.executeUpdate("UPDATE Orders SET orderStatus = '" + status + "', staffUsername = '" + username + "' WHERE orderNo = "+ orderNo);
            closeConnection(con);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //---------------------------------------------------------------------
    //END OF STAFF PAGE FUNCTIONS
    //---------------------------------------------------------------------



    //---------------------------------------------------------------------
    //VIEW CUSTOMER PAGE FUNCTIONS
    //---------------------------------------------------------------------

    public static ArrayList<String> custAddrSelectAll(){    //returns an array list of customer AND ALL address info
        try {
            Connection con = DBDriver.getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            ArrayList<String> orderList = new ArrayList<>();
            ResultSet rs = stmt.executeQuery("SELECT customerId, forename, surname, Customer.houseNo, " +
                    "Address.streetName, Address.cityName, Customer.postcode FROM Customer " +
                    "INNER JOIN Address ON Customer.houseNo=Address.houseNo AND Customer.postcode=Address.postcode;");
            while (rs.next()) {
                orderList.add(
                        rs.getInt("customerId")+ ","+rs.getString("forename")+","+
                                rs.getString("surname")+","+rs.getString("houseNo")+","+
                                rs.getString("streetName")+","+rs.getString("cityName")+","+
                                rs.getString("postcode"));
            }
            closeConnection(con);
            return orderList;
        } catch (SQLException ex) {
            System.out.println("Connection to Database unsuccessful");
            ex.printStackTrace();
            return new ArrayList<>(); //returns empty list if cant connect
        }
    }

    public static ArrayList<String> custAddrSelectAll(int columnIndex, String input){    //returns a sorted array list of customer AND ALL address info
        try {
            Connection con = DBDriver.getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            ArrayList<String> orderList = new ArrayList<>();
            String tableColumn = "Customer.customerId";
            if (columnIndex == 0) {
                tableColumn = "Customer.customerId";
            } else if (columnIndex == 1) {
                tableColumn = "Customer.forename";
            } else if (columnIndex == 2) {
                tableColumn = "Customer.surname";
            } else if (columnIndex == 3) {
                tableColumn = "Customer.houseNo";
            } else if (columnIndex == 4) {
                tableColumn = "Address.streetName";
            } else if (columnIndex == 5) {
                tableColumn = "Address.cityName";
            } else if (columnIndex == 6) {
                tableColumn = "Customer.postcode";
            }
            ResultSet rs = stmt.executeQuery("SELECT customerId, forename, surname, Customer.houseNo, " +
                    "Address.streetName, Address.cityName, Customer.postcode FROM Customer " +
                    "INNER JOIN Address ON Customer.houseNo=Address.houseNo AND Customer.postcode=Address.postcode " +
                    "WHERE " + tableColumn + " LIKE '%" + input + "%';");
            if (rs != null){
                while (rs.next()) {
                    orderList.add(
                            rs.getInt("customerId")+ ","+rs.getString("forename")+","+
                                    rs.getString("surname")+","+rs.getString("houseNo")+","+
                                    rs.getString("streetName")+","+rs.getString("cityName")+","+
                                    rs.getString("postcode"));
                }
                closeConnection(con);
                return orderList;
            } else {
                closeConnection(con);
                return new ArrayList<>();
            }
        } catch (SQLException ex) {
            System.out.println("Connection to Database unsuccessful");
            ex.printStackTrace();
            return new ArrayList<>(); //returns empty list if cant connect
        }
    }

    //---------------------------------------------------------------------
    //END OF VIEW CUSTOMER PAGE FUNCTIONS
    //---------------------------------------------------------------------



    //---------------------------------------------------------------------
    //VIEW INVENTORY PAGE FUNCTIONS
    //---------------------------------------------------------------------

    public static ArrayList<String> handleBarSelectAll() {
        ArrayList<String> handleBars = new ArrayList<>();
        try {
            Connection con = DBDriver.getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Customer INNER JOIN Address ON Customer.houseNo = Address.houseNo AND Customer.postcode = Address.postcode WHERE customerId = ");
            rs.next();
            handleBars.add(rs.getString("forename"));
            handleBars.add(rs.getString("surname"));
            handleBars.add(rs.getString("houseNo"));
            handleBars.add(rs.getString("streetName"));
            handleBars.add(rs.getString("cityName"));
            handleBars.add(rs.getString("postcode"));
            con.close();
            return handleBars;
        } catch (SQLException ex) {
            return handleBars;
        }
    }

    //---------------------------------------------------------------------
    //END OF VIEW INVENTORY PAGE FUNCTIONS
    //---------------------------------------------------------------------



    //---------------------------------------------------------------------
    //BROWSE PAGE FUNCTIONS
    //---------------------------------------------------------------------

    public static ArrayList<String> allFramesInStock(){
        try {
            Connection con = DBDriver.getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            ArrayList<String> frameList = new ArrayList<>();
            ResultSet rs = stmt.executeQuery("SELECT frameId, brandName, productName, gears, shocks, size, unitCost FROM FrameSet INNER JOIN Product ON frameId = productId WHERE stock > 0;");
            while (rs.next()) {
                frameList.add(rs.getInt("frameId")+". "+rs.getString("brandName")+" '"+rs.getString("productName")+
                        "' - Gears: "+rs.getInt("gears")+", Shocks: "+boolIntToString(rs.getInt("shocks"))+", Size: "+
                        rs.getDouble("size")+", Cost: £"+rs.getDouble("unitCost"));
            }
            closeConnection(con);
            return frameList;
        } catch(SQLException ex) {
            System.out.println("Connection to Database unsuccessful");
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static ArrayList<String> allWheelsInStock(){
        try {
            Connection con = DBDriver.getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            ArrayList<String> wheelList = new ArrayList<>();
            ResultSet rs = stmt.executeQuery("SELECT wheelId, brandName, productName, style, brakes, diameter, unitCost FROM Wheel INNER JOIN Product ON wheelId = productId WHERE stock > 1;");
            while (rs.next()) {
                wheelList.add(rs.getInt("wheelId")+". "+rs.getString("brandName")+" '"+rs.getString("productName")+
                        "' - Style: "+rs.getString("style")+", Brakes: "+rs.getString("brakes")+", Diameter: "+
                        rs.getDouble("diameter")+", Cost: £"+rs.getDouble("unitCost"));
            }
            closeConnection(con);
            return wheelList;
        } catch(SQLException ex) {
            System.out.println("Connection to Database unsuccessful");
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static ArrayList<String> allHandlebarsInStock(){
        try {
            Connection con = DBDriver.getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            ArrayList<String> handlebarList = new ArrayList<>();
            ResultSet rs = stmt.executeQuery("SELECT handlebarId, brandName, productName, handlebarStyle, unitCost FROM Handlebar INNER JOIN Product ON handlebarId = productId WHERE stock > 0;");
            while (rs.next()) {
                handlebarList.add(rs.getInt("handlebarId")+". "+rs.getString("brandName")+" '"+rs.getString("productName")+
                        "' - Style: "+rs.getString("handlebarStyle")+", Cost: £"+rs.getDouble("unitCost"));
            }
            closeConnection(con);
            return handlebarList;
        } catch (SQLException ex) {
            System.out.println("Connection to Database unsuccessful");
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static ArrayList<String> allAssembledBikes() {
        try {
            Connection con = DBDriver.getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            ArrayList<String> bikeList = new ArrayList<>();
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
                    "INNER JOIN Product AS wPro ON Wheel.wheelId = wPro.productId");
            while (rs.next()) {
                bikeList.add(rs.getInt("assembledBikeId")+". "+rs.getString("brandName")+" '"+rs.getString("productName")+
                        "' - Frame Gears: "+rs.getInt("frameGears")+", Frame Shocks: "+boolIntToString(rs.getInt("frameShocks"))+
                        ", Frame Size: "+rs.getDouble("frameSize")+", Handlebar Style: "+rs.getString("handlebarStyle")+
                        ", Wheel Diameter: "+rs.getDouble("wheelDiameter")+", Wheel Brakes: "+rs.getString("wheelBrakes")+
                        ", Wheel Style: "+rs.getString("wheelStyle")+", Total Cost: £"+rs.getDouble("total"));
            }
            closeConnection(con);
            return bikeList;
        } catch (SQLException ex) {
            System.out.println("Connection to Database unsuccessful");
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static String[] getBike(int bikeId) {
        try {
            Connection con = DBDriver.getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            String[] bike = new String[5];
            ResultSet rs = stmt.executeQuery("SELECT concat(aPro.brandName,' ', aPro.productName) AS name," +
                    "concat(fPro.brandName,' - ', fPro.productName,', Gears: ', gears,', Shocks: ', shocks,', Size: ', size,', Cost: £', fPro.unitCost) as f," +
                    "concat(hPro.brandName,' - ', hPro.productName,', Style: ',handlebarStyle,', Cost: £',hPro.unitCost) as h," +
                    "concat(wPro.brandName,' - ', hPro.productName,', ',diameter,', ',brakes,', ', style,', Cost: £',wPro.unitCost) as w," +
                    "concat('£',ROUND(aPro.unitCost+fPro.unitCost+hPro.unitCost+wPro.unitCost+wPro.unitCost,2)) AS total FROM AssembledBike " +
                    "INNER JOIN Product AS aPro ON assembledBikeId = aPro.productId " +
                    "INNER JOIN FrameSet ON AssembledBike.frameId = FrameSet.frameId " +
                    "INNER JOIN Handlebar ON AssembledBike.handlebarId = Handlebar.handlebarId " +
                    "INNER JOIN Wheel ON AssembledBike.wheelId = Wheel.wheelId " +
                    "INNER JOIN Product AS fPro ON FrameSet.frameId = fPro.productId " +
                    "INNER JOIN Product AS hPro ON Handlebar.handlebarId = hPro.productId " +
                    "INNER JOIN Product AS wPro ON Wheel.wheelId = wPro.productId WHERE assembledBikeId = "+bikeId);
            rs.next();
            bike[0] = rs.getString("name");
            bike[1] = rs.getString("f");
            bike[2] = rs.getString("h");
            bike[3] = rs.getString("w");
            bike[4] = rs.getString("total");
            closeConnection(con);
            return bike;
        } catch (SQLException ex) {
            System.out.println("Connection to Database unsuccessful");
            ex.printStackTrace();
            String[] bike = new String[5];
            return bike;
        }
    }

    public static void createOrder(int customerId, int bikeId, double cost)
    {

        try {
            Connection con = getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String sqlDate = ""+localDate.getYear()+"-"+localDate.getMonthValue()+"-"+localDate.getDayOfMonth();
            String intoTable = "INSERT INTO Orders(assembledBikeId, customerId, staffUsername, orderDate, orderStatus, orderCost) VALUES(";
            String sql = intoTable + bikeId + "," + customerId + "," + "'TEMP'" + ",'" + sqlDate + "'," + "'Pending'"+","+cost+")";
            System.out.println("Inserting record into the Customer table...");
            stmt.executeUpdate(sql);
            System.out.println("Order insertion successful");
            closeConnection(con);
        } catch (SQLException ex) {
            System.out.println("Order insertion unsuccessful");
            ex.printStackTrace();
        }
    }

    public static void createBike(int frameId, int handlebarId, int wheelId, String name)
    {
        try {
            //CREATE BIKE IN PRODUCT TABLE
            String bikeBrand = getFrameBrand(frameId)+"-"+getWheelType(wheelId);
            createProduct(bikeBrand,name,10,0);
            int bikeProductId = getLatestProductId();
            Connection con = getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            //CREATE BIKE IN
            String intoTable = "INSERT INTO AssembledBike(assembledBikeId, wheelId, handlebarId, frameId) VALUES(";
            String sql = intoTable + bikeProductId + "," + wheelId + "," + handlebarId + "," + frameId+")";
            System.out.println("Inserting record into the AssembledBike table...");
            stmt.executeUpdate(sql);
            System.out.println("bike insertion successful");
            closeConnection(con);
        } catch (SQLException ex) {
            System.out.println("bike insertion unsuccessful");
            ex.printStackTrace();
        }
    }

    public static String getFrameBrand(int frameId)
    {
        try
        {
            Connection con = getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            ResultSet rs = stmt.executeQuery("SELECT brandName FROM FrameSet INNER JOIN Product ON FrameSet.frameId = Product.productId WHERE frameId = "+frameId);
            rs.next();
            String brand = rs.getString("brandName");
            closeConnection(con);
            return brand;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return "";
        }
    }

    public static String getWheelType(int wheelId)
    {
        try
        {
            Connection con = getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            ResultSet rs = stmt.executeQuery("SELECT style FROM Wheel WHERE wheelId = "+wheelId);
            rs.next();
            String style = rs.getString("style");
            closeConnection(con);
            return style;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return "";
        }
    }

    public static int getLatestProductId()
    {
        try
        {
            Connection con = getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Product");
            //ResultSet rs = stmt.executeQuery("SELECT productId FROM Product"); DOESNT WORK????? I DONT UNDERSTAND
            int i = 0;
            while(rs.next())
            {
                i = rs.getInt("productId");
            }
            closeConnection(con);
            return i;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return 0;
        }
    }

    public static void createProduct(String brandName, String productName, double cost, int stock)
    {
        try
        {
            Connection con = getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            int validSerialNo = getNewSerialFromBrand(brandName);
            String intoTable = "INSERT INTO Product(serialNo, brandName, productName, unitCost, stock) VALUES(";
            String sql = intoTable + validSerialNo + ",'" + brandName + "','" + productName + "'," + cost + "," + stock+")";
            stmt.executeUpdate(sql);
            System.out.println("Product Insertion Successful");
            closeConnection(con);
        }
        catch (SQLException ex)
        {
            System.out.println("Product insertion unsuccessful");
            ex.printStackTrace();
        }
    }

    public static int getNewSerialFromBrand(String brandName)
    {
        try
        {
            Connection con = getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            ResultSet rs = stmt.executeQuery("SELECT serialNo FROM Product WHERE brandName = '"+brandName+"'");
            int max = 0;
            while (rs.next())
            {
                int i = rs.getInt("serialNo");
                if(i>max)
                {
                    max = i;
                }
            }
            closeConnection(con);
            return max + 1;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return 0;
        }
    }

    public static int getLatestOrderId(){
        try{
            Connection con = getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            int orderId = 0;
            ResultSet rs = stmt.executeQuery("SELECT orderNo FROM Orders");
            while(rs.next())
            {
                orderId = rs.getInt("orderNo");
            }
            closeConnection(con);
            return orderId;
        }
        catch (SQLException ex){
            System.out.println("Order fetch unsuccessful");
            ex.printStackTrace();
            return 0;
        }
    }

    public static ArrayList<String> allOrdersFromCustomer(int customerId){
        try {
            Connection con = DBDriver.getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            ArrayList<String> orderList = new ArrayList<>();
            ResultSet rs = stmt.executeQuery("SELECT orderNo, CONCAT(fPro.brandName,' - ', fPro.productName) AS frameName, CONCAT(hPro.brandName,' - ', hPro.productName) AS handlebarName,CONCAT(wPro.brandName,' - ', wPro.productName) AS wheelName, orderDate, orderCost, orderStatus FROM Orders " +
                    "INNER JOIN AssembledBike ON AssembledBike.assembledBikeId = Orders.assembledBikeId " +
                    "INNER JOIN FrameSet ON AssembledBike.frameId = FrameSet.frameId " +
                    "INNER JOIN Handlebar ON AssembledBike.handlebarId = Handlebar.handlebarId " +
                    "INNER JOIN Wheel ON AssembledBike.wheelId = Wheel.wheelId " +
                    "INNER JOIN Product AS fPro ON FrameSet.frameId = fPro.productId " +
                    "INNER JOIN Product AS hPro ON Handlebar.handlebarId = hPro.productId " +
                    "INNER JOIN Product AS wPro ON Wheel.wheelId = wPro.productId WHERE customerId = "+customerId);
            while (rs.next()) {
                orderList.add(rs.getInt("orderNo")+ ","+rs.getString("orderDate")+","+rs.getString("frameName")+","+rs.getString("handlebarName")+
                        ","+rs.getString("wheelName")+",£"+rs.getDouble("orderCost")+","+rs.getString("orderStatus"));
            }
            closeConnection(con);
            return orderList;
        } catch (SQLException ex) {
            System.out.println("Connection to Database unsuccessful");
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void DeleteOrder(int orderNo) {
        try {
            Connection con = DBDriver.getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            int count = stmt.executeUpdate("DELETE FROM Orders WHERE orderNo = "+orderNo);
            closeConnection(con);
        } catch (SQLException ex) {
            System.out.println("Connection to Database unsuccessful");
            ex.printStackTrace();
        }
    }

    public static void UpdateCustomer(int customerId,String forename, String surname, String houseNo, String streetName, String cityName, String postcode) {
        try {
            Connection con = getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            if(!addressExists(houseNo, postcode)){   //Check if the Address exists
                addressInsertRecord(houseNo, streetName, cityName, postcode);   //Add Address
            }
            System.out.println("Updating customer record...");  //Add Customer
            int count = stmt.executeUpdate("UPDATE Customer SET forename = '"+forename+"', surname = '"+surname+"', houseNo = '"+houseNo+"', postcode = '"+postcode+"' WHERE customerId = "+customerId);
            System.out.println("Customer update successful");
            closeConnection(con);
        } catch (SQLException ex) {
            System.out.println("Customer update unsuccessful");
            ex.printStackTrace();
        }
    }


    public static String boolIntToString(int tinyInt) {
        if(tinyInt == 1){
            return "Yes";
        } else {
            return "No";
        }
    }

    //---------------------------------------------------------------------
    //END OF BROWSE PAGE FUNCTIONS
    //---------------------------------------------------------------------



    //---------------------------------------------------------------------
    //CUSTOMER LOGIN AND CUSTOMER PAGE FUNCTIONS
    //---------------------------------------------------------------------

    public static boolean orderExists(int orderNo) {
        try {
            Connection con = DBDriver.getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Orders WHERE orderNo = " + orderNo);
            boolean exists = rs.next();
            con.close();
            return exists;
        } catch(SQLException ex) {
            System.out.println("Error with Database connection");
            ex.printStackTrace();
            return false;
        }
    }

    public static int customerFromOrder(int orderNo) {
        try {
            Connection con = DBDriver.getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            ResultSet rs = stmt.executeQuery("SELECT customerId FROM Orders WHERE orderNo = " + orderNo);
            rs.next();
            int customerId = rs.getInt("customerId");
            con.close();
            return customerId;
        } catch (SQLException ex) {
            return 0;
        }
    }

    public static ArrayList<String> customerDetails(int customerId) {
        ArrayList<String> selectedCustomer = new ArrayList<>();
        try {
            Connection con = DBDriver.getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Customer INNER JOIN Address ON Customer.houseNo = Address.houseNo AND Customer.postcode = Address.postcode WHERE customerId = "+customerId);
            rs.next();
            selectedCustomer.add(rs.getString("forename"));
            selectedCustomer.add(rs.getString("surname"));
            selectedCustomer.add(rs.getString("houseNo"));
            selectedCustomer.add(rs.getString("streetName"));
            selectedCustomer.add(rs.getString("cityName"));
            selectedCustomer.add(rs.getString("postcode"));
            con.close();
            return selectedCustomer;
        } catch (SQLException ex) {
            return selectedCustomer;
        }
    }

    public static int getNewCustomerId(){
        try{
            Connection con = DBDriver.getConnection();
            Statement stmt = Objects.requireNonNull(con).createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Customer");
            int lastId = 0;
            while (rs.next())
            {
                lastId = rs.getInt("customerId");
            }
            return lastId;
        }
        catch(SQLException ex){
            return 0;
        }
    }

    //---------------------------------------------------------------------
    //END OF CUSTOMER LOGIN AND CUSTOMER PAGE FUNCTIONS
    //---------------------------------------------------------------------



    //---------------------------------------------------------------------
    //VALIDATION FUNCTIONS
    //---------------------------------------------------------------------

    public static boolean isAlpha(String name) {
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAlphaOrSpace(String name) {
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if(!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }

    public static boolean isNo(String no) {
        try {
            Integer.parseInt(no);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean isNoOrAlpha(String no) {
        char[] chars = no.toCharArray();
        for (char c : chars) {
            try {
                Integer.parseInt(""+c);
            } catch (NumberFormatException ex) {
                if(!Character.isLetter(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isPostcode(String postcode) {
        String regex = "^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(postcode);
        return matcher.matches();
    }

    public static int confirm(String message) {
        return JOptionPane.showConfirmDialog(null, message, "alert", JOptionPane.YES_NO_OPTION);
    }

    //---------------------------------------------------------------------
    //END OF VALIDATION FUNCTIONS
    //---------------------------------------------------------------------


    public static void main(String[] args)
    {
        System.out.println(getLatestProductId());
    }

}