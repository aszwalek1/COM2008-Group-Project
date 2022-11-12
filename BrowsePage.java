import javax.swing.*;
import java.awt.*;

public class BrowsePage {

    //Frame
    JFrame f = new JFrame("Build Your Bike");

    //Buttons
    JButton backButton = new JButton("Back");
    JButton centralOrderButton = new JButton("Order");
    JButton eastOrderButton = new JButton("Order");

    //Panels
    JPanel mainPanel = new JPanel(new GridLayout(1, 3));
    JPanel westPanel = new JPanel();
    JPanel centralPanel = new JPanel();
    JPanel eastPanel = new JPanel();

    public BrowsePage() {

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.add(westPanel);
        mainPanel.add(centralPanel);
        mainPanel.add(eastPanel);

        //Setting layout
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));


        //  WEST PANEL

        westPanel.add(backButton);
        backButton.setMargin(new Insets(5,5,5,5));
        backButton.setBackground(new Color(59, 89, 182));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 12));

        backButton.addActionListener(al -> {
            f.dispose();
            new HomePage();
        });


        //  CENTRAL PANEL

        // label in the middle panel
        centralPanel.add(Box.createRigidArea(new Dimension(50, 40)));
        JLabel centralLabel = new JLabel("Choose a ready bike to order");
        centralLabel.setFont(new Font("Verdana",Font.BOLD, 18));
        centralPanel.add(centralLabel);

        //Dropdown options and order button
        centralPanel.add(Box.createRigidArea(new Dimension(50, 100)));
        String[] bikeStrings = { "Bike1", "Bike2", "Bike3", "Bike4", "Bike5" };
        JComboBox<String> bikeList = new JComboBox<>(bikeStrings);
        centralPanel.add(bikeList);
        centralPanel.add(Box.createRigidArea(new Dimension(50, 100)));

        //Order button in the central panel
        centralPanel.add(centralOrderButton);
        centralOrderButton.setMargin(new Insets(5,5,5,5));
        centralOrderButton.setBackground(new Color(59, 89, 182));
        centralOrderButton.setForeground(Color.WHITE);
        centralOrderButton.setFont(new Font("Arial", Font.BOLD, 20));


        centralPanel.add(Box.createRigidArea(new Dimension(50, 700)));

        //Action Listener for the Order button
        centralOrderButton.addActionListener(e -> {
            f.dispose();
            new PaymentPage();
        });


        // EAST PANEL

        //Dropdown components options and order button
        eastPanel.add(Box.createRigidArea(new Dimension(50, 40)));
        JLabel eastLabel = new JLabel("Customise your bike by choosing the components       ");
        eastLabel.setFont(new Font("Verdana",Font.BOLD, 18));
        eastPanel.add(eastLabel);
        eastPanel.add(Box.createRigidArea(new Dimension(50, 100)));
        String[] frameString = { "frame set size1", "frame set size2", "frame set size3", "frame set size4"};
        JComboBox<String> frameSetList = new JComboBox<>(frameString);
        eastPanel.add(frameSetList);
        eastPanel.add(Box.createRigidArea(new Dimension(50, 50)));

        //Radio Buttons for choosing shocks option
        JRadioButton withShocks = new JRadioButton("With shocks");
        JRadioButton withoutShocks = new JRadioButton("No shocks");
        ButtonGroup group = new ButtonGroup();
        withShocks.setSelected(true);
        group.add(withShocks);
        group.add(withoutShocks);
        eastPanel.add(withShocks);
        eastPanel.add(withoutShocks);
        eastPanel.add(Box.createRigidArea(new Dimension(200, 50)));

        String[] handlebarStrings = { "Straight Handlebar", "High Handlebar", "Dropped Handlebar" };
        JComboBox<String> handlebarList = new JComboBox<>(handlebarStrings);
        eastPanel.add(handlebarList);
        eastPanel.add(Box.createRigidArea(new Dimension(200, 100)));


        String[] wheelSizeStrings = { "20cm", "30cm", "40cm", "50cm", "60cm" };
        JComboBox<String> wheelSizeList = new JComboBox<>(wheelSizeStrings);
        eastPanel.add(wheelSizeList);
        eastPanel.add(Box.createRigidArea(new Dimension(200, 100)));

        String[] wheelStyleStrings = { "Road Style", "Mountain Style", "Hybrid Style" };
        JComboBox<String> wheelStyleList = new JComboBox<>(wheelStyleStrings);
        eastPanel.add(wheelStyleList);
        eastPanel.add(Box.createRigidArea(new Dimension(200, 50)));

        eastPanel.add(eastOrderButton);
        eastOrderButton.setMargin(new Insets(5,5,5,5));
        eastOrderButton.setBackground(new Color(59, 89, 182));
        eastOrderButton.setForeground(Color.WHITE);
        eastOrderButton.setFont(new Font("Arial", Font.BOLD, 20));

        eastPanel.add(Box.createRigidArea(new Dimension(200, 150)));

        //Action Listener for the Order button

        /*
            This should go to the payment page if it is a logged in customer
            or go to register page if no one is logged in

         */
        eastOrderButton.addActionListener(e -> {
            f.dispose();
            new PaymentPage();
        });



        //add panels to the frame
        f.add(westPanel, BorderLayout.WEST);
        f.add(centralPanel, BorderLayout.CENTER);
        f.add(eastPanel, BorderLayout.EAST);


        f.pack();
        f.setVisible(true);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);


    }
}


        /*          USING DATABASE FOR DROP-DOWNS ??
        //Drop-Down menu for ready bikes
        //Get Bikes from a table from the Database
        ArrayList<String> readyBikes = new ArrayList<>();
        try {
            Connection con = DBDriver.getConnection();
            String query = "select bikes from DBTable";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                //shows Name data in combobox
                //Add data to the array list
                readyBikes.add(rs.getString("BikeID")); //?????????????????
            }
            pst.close();
            DBDriver.closeConnection(con);
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
        return readyBikes;
        */
